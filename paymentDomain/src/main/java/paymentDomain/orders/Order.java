package paymentDomain.orders;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 订单
 *  
 * 2016-6-25
 */
public class Order {
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 订单金额
	 */
	private BigDecimal amount;
	/**
	 * 订单状态
	 */
	private int status;
	/**
	 * 商家号
	 */
	private Long merchantId;
	/**
	 * 商家第你单号
	 */
	private String merchantOrderNo;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 订单日期
	 */
	private Date orderDate;
	/**
	 * 支付日期
	 */
	private Date payDate;
	/**
	 * 签名类型
	 */
	private String signType;
	
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}
	public void setMerchantOrderNo(String merchantOrderNo) {
		this.merchantOrderNo = merchantOrderNo;
	}
}
