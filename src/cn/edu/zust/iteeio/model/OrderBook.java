package cn.edu.zust.iteeio.model;

public class OrderBook {
	private long bookId;
	private long orderId;
	private long totalQty;
	private Double totalPrice;
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(long totalQty) {
		this.totalQty = totalQty;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
