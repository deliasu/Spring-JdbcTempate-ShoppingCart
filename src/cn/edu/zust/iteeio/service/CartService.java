package cn.edu.zust.iteeio.service;

import cn.edu.zust.iteeio.model.CartItem;
/**
 * Created by Eliasu
 */
public interface CartService {
	public CartItem increaseQty(long id);
	public CartItem decreaseQty(long id);
	public void delete(long id);
	boolean allReadyInCart(long id, int qty);
}
