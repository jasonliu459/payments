package common.utils.validate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.logging.LoggerFactoryL4JImpl;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;

import common.utils.validate.ValidateException.ErrorDO;

public class ValidateHelper {
	
	private static Validator vali;
	
	static{
		Validator.setLoggerFactory(new LoggerFactoryL4JImpl());
		vali=new ValidatorFix();
	}

	/**
	 * 判断是否通过校验
	 * @param obj
	 * @param profiles
	 * @return
	 */
	public static boolean isPass(Object obj,final String... profiles){
		if(obj==null){
			return false;
		}
		List<ConstraintViolation> ret=null;
		if(profiles==null){
			ret=vali.validate(obj);
		}else{
			ret=vali.validate(obj, profiles);
		}
		if(ret.size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 断言能通过校验
	 * @param obj
	 * @param profiles
	 * @return
	 */
	public static void asertPass(Object obj,final String... profiles){
		if(obj==null){
			fail(null,"校验对象不能为空");
		}
		List<ConstraintViolation> ret=null;
		try {
			if(profiles==null||profiles.length==0){
				ret=vali.validate(obj);
			}else{
				ret=vali.validate(obj, profiles);
			}
		} catch (Exception e) {
			fail(null,"参数校验异常");
		}
		if(ret.size()>0){
			throw transferException(ret);
		}
	}
	
	/**
	 * 断言参数不为空
	 * @param name
	 * @param obj
	 */
	public static <T> T assertNotNull(String code,String errerMsg,T obj){
		if(obj==null){
			fail(code,errerMsg);
		}
		if(obj instanceof String && StringUtils.isEmpty(obj.toString())){
			fail(code,errerMsg);
		}
		return obj;
	}
	
	
	/**
	 * 断言空
	 * @param message
	 * @param object
	 */
	public static void assertNull(String code,String message, Object object) {
        if (object == null) {
            return;
        }
        fail(code,message);
    }
	
	/**
	 * 断言相等
	 * @param <T>
	 * @param message
	 * @param expected
	 * @param actual
	 * @return
	 */
	public static <T> T assertEquals(String code,String message, T expected, T actual) {
		try {
			MatcherAssert.assertThat(actual, CoreMatchers.equalTo(expected));
			return actual;
		} catch (Throwable e) {
			fail(code,message);
			return null;
		}
    }
	
	/**
	 * 断言不相等
	 * @param <T>
	 * @param message
	 * @param expected
	 * @param actual
	 * @return
	 */
	public static <T> T assertNotEquals(String code,String message, T expected, T actual) {
		try {
			MatcherAssert.assertThat(actual, CoreMatchers.not(expected));
			return actual;
		} catch (Throwable e) {
			fail(code,message);
			return null;
		}
	}
	
	/**
	 * 断言为真
	 * @param message
	 * @param condition
	 */
	public static void assertTrue(String code,String message, boolean condition) {
        if (!condition) {
        	fail(code,message);
        }
    }
	
	/**
	 * 断言为假
	 * @param message
	 * @param condition
	 */
	public static void assertFalse(String code,String message, boolean condition) {
        assertTrue(code,message, !condition);
    }
	
	/**
	 * 断言在集合中
	 * @param <T>
	 * @param obj
	 * @param target
	 * @param errerMsg
	 * @return
	 */
	public static <T> T assertIn(String code,String errerMsg,T obj,T... target){
		if(obj==null||target==null){
			fail(code,errerMsg);
		}
		
		if(!Arrays.asList(target).contains(obj)){
			fail(code,errerMsg);
		}
		
		return obj;
	}
	
	/**
	 * 断言不在集合中
	 * @param <T>
	 * @param obj
	 * @param target
	 * @param errerMsg
	 * @return
	 */
	public static <T> T assertNotIn(String code,String errerMsg,T obj,T... target){
		if(obj==null||target==null){
			return obj;
		}
		
		if(Arrays.asList(target).contains(obj)){
			fail(code,errerMsg);
		}
		
		return obj;
	}
	
	/**
	 * 失败处理
	 * @param errerMsg
	 */
	private static void fail(String code,String errerMsg){
		throw new ValidateException(code,errerMsg==null?"":errerMsg);
	}
	
	/**
	 * 异常转换
	 * @param ret
	 * @return
	 */
	private static ValidateException transferException(List<ConstraintViolation> ret){
		List<ErrorDO> errors=new ArrayList<ErrorDO>();
		for(ConstraintViolation c:ret){
			errors.add(new ErrorDO(c.getErrorCode(), c.getMessage()));
		}
		return new ValidateException(errors);
	}
}
