package paymentDomain.account.domain;

import java.math.BigDecimal;

/**
 * <p>Title:账务流水记录 			</p>* 
 *	<p>Description: 	</p>* 
 * 	By Jason * 2016-6-25 	*
 */
public class AccountRecords {
	/**
	 * 账户id
	 */
	private Long accountId;
	/**
	 * 账务变动类型
	 */
	private int accountChangeType;
	/**
	 * 可用余额变动
	 */
	private BigDecimal availableAmountChange;
	/**
	 * 总金额变动
	 */
	private BigDecimal totleAmountChange;
	/**
	 * 冻结金额变动
	 */
	private BigDecimal frozAmountChange;
	/**
	 * 业务发生时间
	 */
	private BigDecimal busiTime;
	/**
	 * 原可用金额
	 */
	private BigDecimal oriAvailableAmount;
	/**
	 * 原总金额
	 */
	private BigDecimal oriTotalAmount;
	/**
	 * 原冻结金额
	 */
	private BigDecimal oriFrozAmount;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public int getAccountChangeType() {
		return accountChangeType;
	}
	public void setAccountChangeType(int accountChangeType) {
		this.accountChangeType = accountChangeType;
	}
	public BigDecimal getAvailableAmountChange() {
		return availableAmountChange;
	}
	public void setAvailableAmountChange(BigDecimal availableAmountChange) {
		this.availableAmountChange = availableAmountChange;
	}
	public BigDecimal getTotleAmountChange() {
		return totleAmountChange;
	}
	public void setTotleAmountChange(BigDecimal totleAmountChange) {
		this.totleAmountChange = totleAmountChange;
	}
	public BigDecimal getFrozAmountChange() {
		return frozAmountChange;
	}
	public void setFrozAmountChange(BigDecimal frozAmountChange) {
		this.frozAmountChange = frozAmountChange;
	}
	public BigDecimal getBusiTime() {
		return busiTime;
	}
	public void setBusiTime(BigDecimal busiTime) {
		this.busiTime = busiTime;
	}
	public BigDecimal getOriAvailableAmount() {
		return oriAvailableAmount;
	}
	public void setOriAvailableAmount(BigDecimal oriAvailableAmount) {
		this.oriAvailableAmount = oriAvailableAmount;
	}
	public BigDecimal getOriTotalAmount() {
		return oriTotalAmount;
	}
	public void setOriTotalAmount(BigDecimal oriTotalAmount) {
		this.oriTotalAmount = oriTotalAmount;
	}
	public BigDecimal getOriFrozAmount() {
		return oriFrozAmount;
	}
	public void setOriFrozAmount(BigDecimal oriFrozAmount) {
		this.oriFrozAmount = oriFrozAmount;
	}
	
	
}
