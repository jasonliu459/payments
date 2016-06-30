package paymentDomain.orders;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算订单
 *  
 * 2016-6-25
 */
public class SettleOrder {
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 结算状态
	 */
	private int settleStatus;
	/**
	 * 结算日期
	 */
	private Date settleDate;
	/**
	 * 结算日
	 */
	private String SettleDay;
	/**
	 * 结算金额
	 */
	private BigDecimal settleAmount;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public int getSettleStatus() {
		return settleStatus;
	}
	public void setSettleStatus(int settleStatus) {
		this.settleStatus = settleStatus;
	}
	public Date getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	public String getSettleDay() {
		return SettleDay;
	}
	public void setSettleDay(String settleDay) {
		SettleDay = settleDay;
	}
	public BigDecimal getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(BigDecimal settleAmount) {
		this.settleAmount = settleAmount;
	}
}
