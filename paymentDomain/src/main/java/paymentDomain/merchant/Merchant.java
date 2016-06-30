package paymentDomain.merchant;
/**
 * 商家
 *  
 * 2016-6-25
 */
public class Merchant {
	/**
	 * 商家号
	 */
	private Long merchantId;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 账户id
	 */
	private Long accountId;
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
}
