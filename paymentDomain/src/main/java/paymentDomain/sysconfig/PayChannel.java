package paymentDomain.sysconfig;

import java.math.BigDecimal;

/**
 * 支付通道
 *  
 * 2016-6-25
 */
public class PayChannel {
	/**
	 * 通道id
	 */
	private Long channelId;
	/**
	 * 通道名称
	 */
	private String channelName;
	/**
	 * 通道code
	 */
	private String channelCode;
	/**
	 * 通道费率
	 */
	private BigDecimal bankCharge;
	/**
	 * 收费类型
	 */
	private int chargeType;
	/**
	 * 通道状态
	 */
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public BigDecimal getBankCharge() {
		return bankCharge;
	}
	public void setBankCharge(BigDecimal bankCharge) {
		this.bankCharge = bankCharge;
	}
	public int getChargeType() {
		return chargeType;
	}
	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}
}
