package cn.edu.zust.iteeio.model;

public class MonthlyReport {
    private Book book;
    private OrderManage orderManage;
    private Double totalCost;
    private Double profit;
    
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public OrderManage getOrderManage() {
		return orderManage;
	}
	public void setOrderManage(OrderManage orderManage) {
		this.orderManage = orderManage;
	}
    
}
