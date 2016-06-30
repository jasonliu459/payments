package paymentDomain.sysconfig;
/**
 * 支付网关通道关联表
 *  
 * 2016-6-25
 */
public class PayChannelGatewayCfg {
	/**
	 * 支付网关id
	 */
	private Long payGatewayId;
	/**
	 * 支付通道id
	 */
	private Long payChannelId;
	public Long getPayGatewayId() {
		return payGatewayId;
	}
	public void setPayGatewayId(Long payGatewayId) {
		this.payGatewayId = payGatewayId;
	}
	public Long getPayChannelId() {
		return payChannelId;
	}
	public void setPayChannelId(Long payChannelId) {
		this.payChannelId = payChannelId;
	}
	
}
