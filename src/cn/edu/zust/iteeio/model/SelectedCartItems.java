package cn.edu.zust.iteeio.model;

public class SelectedCartItems {
	private Book selectedBook;
	private int selectedQty;
	private double selectedTotalPrice;
	public Book getSelectedBook() {
		return selectedBook;
	}
	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	public int getSelectedQty() {
		return selectedQty;
	}
	public void setSelectedQty(int selectedQty) {
		this.selectedQty = selectedQty;
	}
	public double getSelectedTotalPrice() {
		return selectedTotalPrice;
	}
	public void setSelectedTotalPrice(double selectedTotalPrice) {
		this.selectedTotalPrice = selectedTotalPrice;
	}
}
