package paymentDomain.ctcenter;
/**
 * 用户
 *  
 * 2016-6-25
 */
public class User {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户密码
	 */
	private String userPwd;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
}
