package common.utils.validate;

import static java.lang.Boolean.TRUE;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import net.sf.oval.Check;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.Configurer;
import net.sf.oval.configuration.pojo.elements.ClassConfiguration;
import net.sf.oval.configuration.pojo.elements.ConstructorConfiguration;
import net.sf.oval.configuration.pojo.elements.FieldConfiguration;
import net.sf.oval.configuration.pojo.elements.MethodConfiguration;
import net.sf.oval.configuration.pojo.elements.ObjectConfiguration;
import net.sf.oval.configuration.pojo.elements.ParameterConfiguration;
import net.sf.oval.constraint.AssertFieldConstraintsCheck;
import net.sf.oval.constraint.NotNullCheck;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.MethodReturnValueContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.FieldNotFoundException;
import net.sf.oval.exception.InvalidConfigurationException;
import net.sf.oval.exception.MethodNotFoundException;
import net.sf.oval.exception.OValException;
import net.sf.oval.exception.ReflectionException;
import net.sf.oval.exception.ValidationFailedException;
import net.sf.oval.internal.ClassChecks;
import net.sf.oval.internal.ContextCache;
import net.sf.oval.internal.util.Assert;
import net.sf.oval.internal.util.ReflectionUtils;

public class ValidatorFix extends Validator {

	protected final Map<Class<?>, ClassChecks> checksByClass = new WeakHashMap<Class<?>, ClassChecks>();

	@Override
	protected void validateInvariants(Object validatedObject, List<ConstraintViolation> violations, String[] profiles) throws IllegalArgumentException,
			ValidationFailedException {
		Assert.argumentNotNull("validatedObject", validatedObject);

		currentlyValidatedObjects.get().getLast().add(validatedObject);
		if (validatedObject instanceof Class<?>) {
			super.validateInvariants(validatedObject, violations, profiles);
		} else {
			this.validateObjectInvariants(validatedObject, validatedObject.getClass(), violations, profiles);
		}
	}

	@Override
	protected ClassChecks getClassChecks(Class<?> clazz) throws IllegalArgumentException, InvalidConfigurationException, ReflectionException {
		Assert.argumentNotNull("clazz", clazz);

		synchronized (checksByClass) {
			ClassChecks cc = checksByClass.get(clazz);

			if (cc == null) {
				cc = new ClassChecks(clazz, parameterNameResolver);

				for (final Configurer configurer : getConfigurers()) {
					Class<?> searchType = clazz;
					while (!Object.class.equals(searchType) && searchType != null) {
						final ClassConfiguration classConfig = configurer.getClassConfiguration(searchType);
						if (classConfig != null) {
							addChecks(cc, classConfig);
						}
						searchType = searchType.getSuperclass();
					}
				}

				checksByClass.put(clazz, cc);
			}

			return cc;
		}
	}

	protected void addChecks(final ClassChecks cc, final ClassConfiguration classCfg) throws InvalidConfigurationException, ReflectionException {
		if (TRUE.equals(classCfg.overwrite)) {
			cc.clear();
		}

		if (classCfg.checkInvariants != null) {
			cc.isCheckInvariants = classCfg.checkInvariants;
		}

		// cache the result for better performance
		final boolean applyFieldConstraintsToConstructors = TRUE.equals(classCfg.applyFieldConstraintsToConstructors);
		final boolean applyFieldConstraintsToSetters = TRUE.equals(classCfg.applyFieldConstraintsToSetters);
		final boolean assertParametersNotNull = TRUE.equals(classCfg.assertParametersNotNull);
		final NotNullCheck sharedNotNullCheck = assertParametersNotNull ? new NotNullCheck() : null;

		try {
			/* ******************************
			 * apply object level checks *****************************
			 */
			if (classCfg.objectConfiguration != null) {
				final ObjectConfiguration objectCfg = classCfg.objectConfiguration;

				if (TRUE.equals(objectCfg.overwrite)) {
					cc.clearObjectChecks();
				}
				cc.addObjectChecks(objectCfg.checks);
			}

			/* ******************************
			 * apply field checks *****************************
			 */
			if (classCfg.fieldConfigurations != null) {
				for (final FieldConfiguration fieldCfg : classCfg.fieldConfigurations) {
					final Field field = classCfg.type.getDeclaredField(fieldCfg.name);

					if (TRUE.equals(fieldCfg.overwrite)) {
						cc.clearFieldChecks(field);
					}
					boolean exist=false;
					for(Field f:cc.constrainedFields){
						if(f.getName().equals(field.getName())){
							exist=true;
						}
					}

					if (fieldCfg.checks != null && fieldCfg.checks.size() > 0 && !exist) {
						cc.addFieldChecks(field, fieldCfg.checks);
					}
				}
			}

			/* ******************************
			 * apply constructor parameter checks *****************************
			 */
			if (classCfg.constructorConfigurations != null) {
				for (final ConstructorConfiguration ctorCfg : classCfg.constructorConfigurations) {
					// ignore constructors without parameters
					if (ctorCfg.parameterConfigurations == null) {
						continue;
					}

					final Class<?>[] paramTypes = new Class[ctorCfg.parameterConfigurations.size()];

					for (int i = 0, l = ctorCfg.parameterConfigurations.size(); i < l; i++) {
						paramTypes[i] = ctorCfg.parameterConfigurations.get(i).type;
					}

					final Constructor<?> ctor = classCfg.type.getDeclaredConstructor(paramTypes);

					if (TRUE.equals(ctorCfg.overwrite)) {
						cc.clearConstructorChecks(ctor);
					}

					if (TRUE.equals(ctorCfg.postCheckInvariants)) {
						cc.methodsWithCheckInvariantsPost.add(ctor);
					}

					final String[] paramNames = parameterNameResolver.getParameterNames(ctor);

					for (int i = 0, l = ctorCfg.parameterConfigurations.size(); i < l; i++) {
						final ParameterConfiguration paramCfg = ctorCfg.parameterConfigurations.get(i);

						if (TRUE.equals(paramCfg.overwrite)) {
							cc.clearConstructorParameterChecks(ctor, i);
						}

						if (paramCfg.hasChecks()) {
							cc.addConstructorParameterChecks(ctor, i, paramCfg.checks);
						}

						if (paramCfg.hasCheckExclusions()) {
							cc.addConstructorParameterCheckExclusions(ctor, i, paramCfg.checkExclusions);
						}

						if (assertParametersNotNull) {
							cc.addConstructorParameterChecks(ctor, i, sharedNotNullCheck);
						}

						/* *******************
						 * applying field constraints to the single parameter of
						 * setter methods ******************
						 */
						if (applyFieldConstraintsToConstructors) {
							final Field field = ReflectionUtils.getField(cc.clazz, paramNames[i]);

							// check if a corresponding field has been found
							if (field != null && paramTypes[i].isAssignableFrom(field.getType())) {
								final AssertFieldConstraintsCheck check = new AssertFieldConstraintsCheck();
								check.setFieldName(field.getName());
								cc.addConstructorParameterChecks(ctor, i, check);
							}
						}
					}
				}
			}

			/* ******************************
			 * apply method parameter and return value checks and pre/post
			 * conditions *****************************
			 */
			if (classCfg.methodConfigurations != null) {
				for (final MethodConfiguration methodCfg : classCfg.methodConfigurations) {
					/* ******************************
					 * determine the method *****************************
					 */
					final Method method;

					if (methodCfg.parameterConfigurations == null || methodCfg.parameterConfigurations.size() == 0) {
						method = classCfg.type.getDeclaredMethod(methodCfg.name);
					} else {
						final Class<?>[] paramTypes = new Class[methodCfg.parameterConfigurations.size()];

						for (int i = 0, l = methodCfg.parameterConfigurations.size(); i < l; i++) {
							paramTypes[i] = methodCfg.parameterConfigurations.get(i).type;
						}

						method = classCfg.type.getDeclaredMethod(methodCfg.name, paramTypes);
					}

					if (TRUE.equals(methodCfg.overwrite)) {
						cc.clearMethodChecks(method);
					}

					/* ******************************
					 * applying field constraints to the single parameter of
					 * setter methods *****************************
					 */
					if (applyFieldConstraintsToSetters) {
						final Field field = ReflectionUtils.getFieldForSetter(method);

						// check if a corresponding field has been found
						if (field != null) {
							final AssertFieldConstraintsCheck check = new AssertFieldConstraintsCheck();
							check.setFieldName(field.getName());
							cc.addMethodParameterChecks(method, 0, check);
						}
					}

					/* ******************************
					 * configure parameter constraints
					 * *****************************
					 */
					if (methodCfg.parameterConfigurations != null && methodCfg.parameterConfigurations.size() > 0) {
						for (int i = 0, l = methodCfg.parameterConfigurations.size(); i < l; i++) {
							final ParameterConfiguration paramCfg = methodCfg.parameterConfigurations.get(i);

							if (TRUE.equals(paramCfg.overwrite)) {
								cc.clearMethodParameterChecks(method, i);
							}

							if (paramCfg.hasChecks()) {
								cc.addMethodParameterChecks(method, i, paramCfg.checks);
							}

							if (paramCfg.hasCheckExclusions()) {
								cc.addMethodParameterCheckExclusions(method, i, paramCfg.checkExclusions);
							}

							if (assertParametersNotNull) {
								cc.addMethodParameterChecks(method, i, sharedNotNullCheck);
							}
						}
					}

					/* ******************************
					 * configure return value constraints
					 * *****************************
					 */
					if (methodCfg.returnValueConfiguration != null) {
						if (TRUE.equals(methodCfg.returnValueConfiguration.overwrite)) {
							cc.clearMethodReturnValueChecks(method);
						}

						if (methodCfg.returnValueConfiguration.checks != null && methodCfg.returnValueConfiguration.checks.size() > 0) {
							cc.addMethodReturnValueChecks(method, methodCfg.isInvariant, methodCfg.returnValueConfiguration.checks);
						}
					}

					if (TRUE.equals(methodCfg.preCheckInvariants)) {
						cc.methodsWithCheckInvariantsPre.add(method);
					}

					/*
					 * configure pre conditions
					 */
					if (methodCfg.preExecutionConfiguration != null) {
						if (TRUE.equals(methodCfg.preExecutionConfiguration.overwrite)) {
							cc.clearMethodPreChecks(method);
						}

						if (methodCfg.preExecutionConfiguration.checks != null && methodCfg.preExecutionConfiguration.checks.size() > 0) {
							cc.addMethodPreChecks(method, methodCfg.preExecutionConfiguration.checks);
						}
					}

					if (TRUE.equals(methodCfg.postCheckInvariants)) {
						cc.methodsWithCheckInvariantsPost.add(method);
					}

					/*
					 * configure post conditions
					 */
					if (methodCfg.postExecutionConfiguration != null) {
						if (TRUE.equals(methodCfg.postExecutionConfiguration.overwrite)) {
							cc.clearMethodPostChecks(method);
						}

						if (methodCfg.postExecutionConfiguration.checks != null && methodCfg.postExecutionConfiguration.checks.size() > 0) {
							cc.addMethodPostChecks(method, methodCfg.postExecutionConfiguration.checks);
						}
					}
				}
			}
		} catch (final NoSuchMethodException ex) {
			throw new MethodNotFoundException(ex);
		} catch (final NoSuchFieldException ex) {
			throw new FieldNotFoundException(ex);
		}
	}

	protected void validateObjectInvariants(final Object validatedObject, final Class<?> clazz, final List<ConstraintViolation> violations,
			final String[] profiles) throws ValidationFailedException {
		assert validatedObject != null;
		assert clazz != null;
		assert violations != null;

		// abort if the root class has been reached
		if (clazz == Object.class)
			return;

		try {
			final ClassChecks cc = getClassChecks(clazz);

			// validate field constraints
			for (final Field field : cc.constrainedFields) {
				final Collection<Check> checks = cc.checksForFields.get(field);

				if (checks != null && checks.size() > 0) {
					final FieldContext ctx = ContextCache.getFieldContext(field);
					final Object valueToValidate = resolveValue(ctx, validatedObject);

					for (final Check check : checks) {
						checkConstraint(violations, check, validatedObject, valueToValidate, ctx, profiles, false);
					}
				}
			}

			// validate constraints on getter methods
			for (final Method getter : cc.constrainedMethods) {
				final Collection<Check> checks = cc.checksForMethodReturnValues.get(getter);

				if (checks != null && checks.size() > 0) {
					final MethodReturnValueContext ctx = ContextCache.getMethodReturnValueContext(getter);
					final Object valueToValidate = resolveValue(ctx, validatedObject);

					for (final Check check : checks) {
						checkConstraint(violations, check, validatedObject, valueToValidate, ctx, profiles, false);
					}
				}
			}

			// validate object constraints
			if (cc.checksForObject.size() > 0) {
				final OValContext ctx = ContextCache.getClassContext(clazz);
				for (final Check check : cc.checksForObject) {
					checkConstraint(violations, check, validatedObject, validatedObject, ctx, profiles, false);
				}
			}

		} catch (final OValException ex) {
			throw new ValidationFailedException("Object validation failed. Class: " + clazz + " Validated object: " + validatedObject, ex);
		}
	}

}
