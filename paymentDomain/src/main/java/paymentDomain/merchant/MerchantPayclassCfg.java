package paymentDomain.merchant;
import java.math.BigDecimal;
/**
 * 商家支付类型
 *  
 * 2016-6-25
 */
public class MerchantPayclassCfg {
	/**
	 * 支付类型
	 */
	private Long payClassId;
	/**
	 * 商户号
	 */
	private Long merchantId;
	/**
	 * 费率
	 */
	private BigDecimal chargeRate;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 结算周期
	 */
	private int settlePeriod;
	/**
	 * 保证金费率
	 */
	private BigDecimal depositRate;
	
	private int depositPeriod;
	
	public Long getPayClassId() {
		return payClassId;
	}
	public void setPayClassId(Long payClassId) {
		this.payClassId = payClassId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getChargeRate() {
		return chargeRate;
	}
	public void setChargeRate(BigDecimal chargeRate) {
		this.chargeRate = chargeRate;
	}
	public int getSettlePeriod() {
		return settlePeriod;
	}
	public void setSettlePeriod(int settlePeriod) {
		this.settlePeriod = settlePeriod;
	}
	public BigDecimal getDepositRate() {
		return depositRate;
	}
	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}
	public int getDepositPeriod() {
		return depositPeriod;
	}
	public void setDepositPeriod(int depositPeriod) {
		this.depositPeriod = depositPeriod;
	}
	
}
