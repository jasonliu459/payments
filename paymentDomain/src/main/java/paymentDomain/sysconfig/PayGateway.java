package paymentDomain.sysconfig;
/**
 * 支付网关
 *  
 * 2016-6-25
 */
public class PayGateway {
	/**
	 * 支付网关id
	 */
	private Long gatewayId;
	/**
	 * 支付网关名称
	 */
	private String gatewayName;
	/**
	 * 支付类型名称
	 */
	private Long payClassId;
	public Long getGatewayId() {
		return gatewayId;
	}
	public void setGatewayId(Long gatewayId) {
		this.gatewayId = gatewayId;
	}
	public String getGatewayName() {
		return gatewayName;
	}
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}
	public Long getPayClassId() {
		return payClassId;
	}
	public void setPayClassId(Long payClassId) {
		this.payClassId = payClassId;
	}
}	
