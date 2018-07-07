package cn.edu.zust.iteeio.service;

import java.util.List;

import cn.edu.zust.iteeio.controller.BookController;
import cn.edu.zust.iteeio.model.CartItem;
/**
 * Created by Eliasu T. (伊拉索)
 */
public class CartServiceImpl implements CartService {
	private List<CartItem> cartItem ;

	
    public List<CartItem> getCartItem() {
		return cartItem;
	}
	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	public CartServiceImpl(){
		try{
			cartItem = BookController.cartItemList;
		}catch(Exception e){
			cartItem = null;
		}
    }

	/**
	 *
	 * @param id
	 * @param qty
	 * @return
	 */
	@Override
	public boolean allReadyInCart(long id, int qty) {
	    if(cartItem != null){
	    	int index = 0;
	    	for(CartItem cItem: cartItem){
	    		if(cItem.getBook().getId()== id){
	    			int stock = BookController.cartItemList.get(index).getBook().getStock();
	    			int totqty = cItem.getQty() + qty;
	    			int newQty;
	    			if(stock >= totqty ){
	    				newQty = totqty;
	    			}else{
	    				newQty = stock;
	    			}
	    			BookController.cartItemList.get(index).setQty(newQty);
	    			
	    			BookController.cartItemList.get(index).setTotalPrice(cItem.getBook().getPrice()*cItem.getQty());
	    			return true;
	    		}
	    		index++;
	    	}
	    }
		return false;
	}

	@Override
	public CartItem increaseQty(long id) {
		
		return null;
	}

	@Override
	public CartItem decreaseQty(long id) {
		
		return null;
	}

	@Override
	public void delete(long id) {
	

	}
	
}
