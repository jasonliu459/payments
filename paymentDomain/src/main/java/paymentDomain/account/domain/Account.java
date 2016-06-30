package paymentDomain.account.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账务表2016-6-25*
 */
public class Account {
	/**
	 * 账户id
	 */
	private long accountId;
	/**
	 * 总金额
	 */
	private BigDecimal totleAmount;
	/**
	 * 可用余额
	 */
	private BigDecimal avaliableAmount;
	/**
	 * 冻结余额
	 */
	private BigDecimal frozAmount;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后修改时间
	 */
	private Date lastUpdateTime;
	/**
	 * 账户类型
	 */
	private int accountType;
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getTotleAmount() {
		return totleAmount;
	}
	public void setTotleAmount(BigDecimal totleAmount) {
		this.totleAmount = totleAmount;
	}
	public BigDecimal getAvaliableAmount() {
		return avaliableAmount;
	}
	public void setAvaliableAmount(BigDecimal avaliableAmount) {
		this.avaliableAmount = avaliableAmount;
	}
	public BigDecimal getFrozAmount() {
		return frozAmount;
	}
	public void setFrozAmount(BigDecimal frozAmount) {
		this.frozAmount = frozAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
}
