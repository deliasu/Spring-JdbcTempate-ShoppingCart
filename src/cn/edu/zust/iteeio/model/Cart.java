package cn.edu.zust.iteeio.model;

import java.util.List;

public class Cart {
	private int grandQty;
	private List<SelectedCartItems> cartItem;
	private double grandTotal;
	public int getGrandQty() {
		return grandQty;
	}
	public void setGrandQty(int grandQty) {
		this.grandQty = grandQty;
	}
	public List<SelectedCartItems> getCartItem() {
		return cartItem;
	}
	public void setCartItem(List<SelectedCartItems> cartItem) {
		this.cartItem = cartItem;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
