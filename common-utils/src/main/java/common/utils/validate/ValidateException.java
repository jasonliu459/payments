package common.utils.validate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidateException extends RuntimeException {

	/** */
	private static final long serialVersionUID = 1L;
	
	private List<ErrorDO> errors;
	
	private String code;

	public ValidateException(List<ErrorDO> errors) {
		super((errors!=null&&errors.size()>0)?errors.get(0).getMsg():"");
		this.errors=errors;
		if(errors!=null&&errors.size()>0){
			code=errors.get(0).getCode();
		}
	}
	
	public ValidateException(String code,String msg) {
		super(msg);
		ErrorDO error=new ErrorDO(code, msg);
		errors=new ArrayList<ErrorDO>();
		errors.add(error);
	}
	
	public ValidateException(String msg) {
		this(null, msg);
	}
	
	public List<ErrorDO> getErrors() {
		return errors;
	}

	public String getCode() {
		return code;
	}

	public static class ErrorDO implements Serializable{
		/** */
		private static final long serialVersionUID = 1L;
		
		private String code;
		private String msg;
		
		public ErrorDO(String code, String msg) {
			super();
			this.code = code;
			this.msg = msg;
		}
		
		public String getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
	}
}
