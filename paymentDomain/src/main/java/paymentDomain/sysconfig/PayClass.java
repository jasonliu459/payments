package paymentDomain.sysconfig;
/**
 * 支付类型
 *  
 * 2016-6-25
 */
public class PayClass {
	/**
	 * 支付类型
	 */
	private Long payClassId;
	/**
	 * 支付类型名称
	 */
	private String payClassName;
	public Long getPayClassId() {
		return payClassId;
	}
	public void setPayClassId(Long payClassId) {
		this.payClassId = payClassId;
	}
	public String getPayClassName() {
		return payClassName;
	}
	public void setPayClassName(String payClassName) {
		this.payClassName = payClassName;
	}
	
}
