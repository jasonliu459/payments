package common.utils.validate.constraint;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;

import common.utils.validate.ValidateHelper;

/**
 * @author Sebastian Thomschke
 */
public class NotEmptyNullCheck extends AbstractAnnotationCheck<NotEmptyNull> {
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}
	 */
	public boolean isSatisfied(final Object validatedObject, final Object valueToValidate, final OValContext context, final Validator validator) {
		try {
			ValidateHelper.assertNotNull(null,"", valueToValidate);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
