package cn.edu.zust.iteeio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zust.iteeio.dao.BookDaoImpl;
import cn.edu.zust.iteeio.model.Book;
import cn.edu.zust.iteeio.model.Cart;
import cn.edu.zust.iteeio.model.CartItem;
import cn.edu.zust.iteeio.model.Customer;
import cn.edu.zust.iteeio.model.Order;
import cn.edu.zust.iteeio.model.OrderBook;
import cn.edu.zust.iteeio.model.SelectedCartItems;
import cn.edu.zust.iteeio.service.CartServiceImpl;

/**
 * Created by Eliasu T. (伊拉索） on 2016/12/10
 */

@Controller
public class BookController {
	public static List<CartItem> cartItemList = null;
	public static List<SelectedCartItems> checkedCartItemsList = null;
	public static List<Cart>  s_cart = null;
	public static Customer customerInfo = null;
	public static List<SelectedCartItems> selectedCart = null;

    /**
     *
     * @param model
     * @return
     */
	@RequestMapping(value="/all-books")
	private ModelAndView getAllBooks(Model model){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		List<Book> allbook = bookDaoImpl.displayAllBooks();
		model.addAttribute("allbooks", allbook);
		model.addAttribute("a", "Good!!");
		model.addAttribute("title", "ETBook all books");
		return new ModelAndView("books");
	}

    /**
     *
     * @param id
     * @param model
     * @return
     */
	@RequestMapping(value="/product-details")
	public String getBookDetail(@RequestParam("id") long id, Model model){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		Book bk = new Book();
		bk = bookDaoImpl.getBookDetail(id);
		if(bk == null){
			return "books"; //change to a book not found view later
		}
		model.addAttribute("book", bk);
		model.addAttribute("title", "ETBook book details");
		return "bookDetail";
	}

    /**
     *
     * @param session
     * @param id
     * @param qty
     */
	@RequestMapping(value="/add-to-cart")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addToCart(HttpSession session, @RequestParam("id") long id, @RequestParam("add-qty") int qty){ 
		if(cartItemList == null){
			cartItemList = new ArrayList<CartItem>();
		}
		if(!session.isNew()){
			session.getAttribute("cartItems");
		}
		
		
		CartServiceImpl cartServuceImpl = new CartServiceImpl();
		if(cartServuceImpl.allReadyInCart(id, qty)){
			// handled in service
		}else{
			CartItem  cartItem = new CartItem();
			BookDaoImpl bookDaoImpl = new BookDaoImpl();
			cartItem.setBook(bookDaoImpl.getBookDetail(id));
			cartItem.setQty(qty);
			cartItem.setTotalPrice(cartItem.getQty() * cartItem.getBook().getPrice());
			cartItemList.add(cartItem);
		}
		session.setAttribute("cartItems", cartItemList);
	}

    /**
     *
     * @param session
     * @param model
     * @return
     */
	@RequestMapping(value="/cart")
	@SuppressWarnings("unchecked")
	public ModelAndView getCart(HttpSession session, Model model){
		List<CartItem> cartItem = (List<CartItem>) session.getAttribute("cartItems");
		model.addAttribute("cart", cartItem);
		model.addAttribute("title", "ETBook Cart");
		return new ModelAndView("cart");
	}

    /**
     *
     * @param id
     * @param quantity
     * @return
     */
    @RequestMapping(value="/update-quantity")
    public @ResponseBody List<CartItem> updateBookquantity(@RequestParam("ID") long id, @RequestParam("QUANTITY") int quantity){
    	List<CartItem> cartList = BookController.cartItemList;
    	int index = 0;
    	if(cartList != null){
    	for(CartItem item: cartList){
    		if(item.getBook().getId()== id){
    			
    			int stock = BookController.cartItemList.get(index).getBook().getStock();
    			int totqty = quantity; 
    			int newQty;
    			if(stock >= totqty ){
    				newQty = totqty;
    			}else{
    				newQty = stock;
    			}
    			BookController.cartItemList.get(index).setQty(newQty);
    			BookController.cartItemList.get(index).setTotalPrice(item.getQty() * item.getBook().getPrice());
    			break;
    		}
    		index++;
    	}
    	
		return BookController.cartItemList;
    	}else{
    		return null;
    	}
    }

    /**
     *
     * @param id
     * @param session
     * @return
     */
	@RequestMapping(value = "/generate-order")
    public @ResponseBody List<Cart> createCustomerOrder(@RequestParam(value="ID[]") Long[] id, HttpSession session){
    	if(selectedCart == null){
    	    selectedCart = new ArrayList<SelectedCartItems>() ;
    	}else{
    		selectedCart.clear();
    	}
    	
    	List<CartItem> cartItems = BookController.cartItemList;
    	int index = 0;
    	
    	int grandQuantity = 0;
    	double grandTotal = 0.0;
    	if(cartItems != null){
    	
    	for(CartItem cartItem: cartItems){
    		for(int i= 0; i< id.length; i++){ 
    			if(id[i] == cartItem.getBook().getId()){
    				SelectedCartItems sel = new SelectedCartItems();
    				sel.setSelectedBook(cartItem.getBook());
    				sel.setSelectedQty(cartItem.getQty());
    				sel.setSelectedTotalPrice(cartItem.getTotalPrice());
    				selectedCart.add(sel);
    				
    				grandQuantity += cartItem.getQty();
    				grandTotal += cartItem.getTotalPrice();
    				
    			}
  
    		}
    		index++;
    	}
    	}
        if(s_cart == null){
        	s_cart = new ArrayList<Cart>();
        }
     
    	if(!session.isNew()){
    		session.getAttribute("selectedItems");
    	}
    	
    	
    	Cart c = new Cart();
    	c.setCartItem(selectedCart);
    	c.setGrandQty(grandQuantity);
    	c.setGrandTotal(grandTotal);
    	s_cart.add(c);
    	
    	session.setAttribute("selectedItems", selectedCart);
    	session.setAttribute("gt", grandQuantity);
    	session.setAttribute("gq", grandQuantity);
	   return s_cart;
    }

    /**
     *
     * @param order
     * @param result
     * @param session
     * @param model
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/create-add-order")
    public ModelAndView addOrder(@ModelAttribute("order") Order order, BindingResult result, HttpSession session, Model model){
		if(result.hasErrors()){
			return new ModelAndView("error");
		}
		List<SelectedCartItems>  s_cart = (List<SelectedCartItems>) session.getAttribute("selectedItems"); 
		if(s_cart == null){
			return new ModelAndView("cart") ;	
		}
		
		//insert into order 
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		OrderBook ob = new OrderBook();
		long orderId = bookDaoImpl.getMaxOrderId();
		ob.setOrderId(orderId);
		order.setOrderId(orderId);
		int index = 0; 
		try{
		for(SelectedCartItems cart: s_cart){
			ob.setBookId(cart.getSelectedBook().getId());
			ob.setTotalPrice(cart.getSelectedTotalPrice());
			ob.setTotalQty(cart.getSelectedQty());
			bookDaoImpl.insertOrderedBook(ob);
			bookDaoImpl.updatePaid(cart.getSelectedQty(), cart.getSelectedBook().getId());
			index++;
		}
		}catch(Exception e){
			selectedCart = null;
			s_cart = null;
			
			return new ModelAndView("redirect:error");
		}
		bookDaoImpl.insertOrder(order);
		selectedCart = null;
		s_cart = null;
		model.addAttribute("title", "ETBook success");
    	return new ModelAndView("redirect:success") ;
    	
    }


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value="/delete-cart-item", method=RequestMethod.POST)
    public @ResponseBody String deleteCartItem(@RequestParam("ID") long id){
    	List<CartItem> cart = BookController.cartItemList;
    	if(cart != null){
    		int index = 0;
    		for(CartItem cartItem: cart){
    			if(cartItem.getBook().getId() == id){
    				cart.remove(index);
    			}
    			index++;
    		}
    		BookController.cartItemList = cart;
    	}
    	return "OK";
    }

    /**
     *
     * @param chars
     * @return
     */
    @RequestMapping(value="/looseSearch")
    public @ResponseBody List<Book> getLooseSearchResult(@RequestParam("CHARS") String chars){
    	BookDaoImpl bookDaoImpl = new BookDaoImpl();
		return bookDaoImpl.looseSearchBooks(chars);
    }

    /**
     *
     * @param search
     * @param model
     * @return
     */
    @RequestMapping(value="/quick-search")
    public String quickSearch(@RequestParam("search") String search, Model model){
    	BookDaoImpl bookDaoImpl = new BookDaoImpl();
    	List<Book> searchResult = bookDaoImpl.looseSearchBooks(search);
    	model.addAttribute("search", searchResult);
		return "search";
    }
    @RequestMapping(value="/login")
    public String login(Model model){
    	model.addAttribute("title", "ETBook Login");
        return "login";
    }
    @RequestMapping(value="/signup")
    public String signup(Model model){
    	model.addAttribute("title", "ETBook Sign Up");
        return "signup";
    }
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/pay-order")
    public String completeOrder(HttpSession session, Model model){
    	List<Cart> s_cart = (List<Cart>)session.getAttribute("selectedItems");
    	if(s_cart == null){
    		return "cart";
    	}
    	model.addAttribute("title", "ETBook Order");
    	return "order";
    }

    /**
     *
     * @param customer
     * @param result
     * @param model
     * @return
     */
   @RequestMapping(value="/customer-signup",  method = RequestMethod.POST)
   public ModelAndView customerSignup(@ModelAttribute("customer") Customer customer, BindingResult result, Model model){
	   if(result.hasErrors()){
			return new ModelAndView("error");
	    }
	   BookDaoImpl bookDaoImpl = new BookDaoImpl();
	   //check if the customer is already registered i.e using email
	   boolean status = bookDaoImpl.isRegisteredCustomer(customer.getEmail());
	   if(status){
		  model.addAttribute("isRegisterd", "You are already registered, Please log in");
		  return new ModelAndView("signup");
	   }
	   int custInsert = bookDaoImpl.insertCustomer(customer);
	   if(custInsert != 1){
		   return new ModelAndView("redirect:error");
	   }
	   model.addAttribute("title", "ETBook Login");
	   return new ModelAndView("redirect:customer-login");
   }

    /**
     *
     * @param request
     * @param session
     * @param model
     * @return
     */
   @RequestMapping(value="/customer-login",  method = RequestMethod.POST)
   public ModelAndView customerLogin(HttpServletRequest request, HttpSession session, Model model){
	   BookDaoImpl bookDaoImpl = new BookDaoImpl();
	   String email = request.getParameter("email");
	   String password = request.getParameter("password");
	   email = email.trim();
	   password = password.trim();
	   Customer cust = bookDaoImpl.customerLogin(email, password);
	   if(cust == null){
		   model.addAttribute("loginFail", "Incorrect Email or Password");
		   return new ModelAndView("login");
	   }else{
		   if(customerInfo == null){
			   customerInfo = new Customer();
		   }
		   if(!session.isNew()){
			  session.getAttribute("customerInfo");
		   }
		   customerInfo = cust;
		   session.setAttribute("customerInfo", customerInfo);
	   }
	   model.addAttribute("title", "ETBook Welcome");
	return new ModelAndView("redirect:welcome");
   }

    /**
     *
     * @param session
     * @param model
     * @return
     */
   @RequestMapping(value="/customer-logout")
   public ModelAndView logout(HttpSession session, Model model){
	   Customer custInfoSession = (Customer) session.getAttribute("customerInfo");
	   if(custInfoSession != null){
		   custInfoSession = null;
	   }
	   model.addAttribute("title", "ETBook goodbye");
	   return new ModelAndView("goodbye");
   }
   @RequestMapping(value="/welcome")
   public String welcome(Model model){
	   model.addAttribute("title", "ETBook Welcome");
	   return "welcome";
   }
   @RequestMapping(value="/success")
   public String success(Model model){
	   s_cart = null;
	   model.addAttribute("title", "ETBook success");
	   return "success";
   }

    /**
     *
     * @param id
     * @param qty
     * @param session
     * @return
     */
   @RequestMapping(value="/add-to-wish-list")
   public String addToWishList(@RequestParam("id") long id, @RequestParam("add-qty") int qty, HttpSession session){
	   Customer cust = (Customer) session.getAttribute("customerInfo");
	   if(cust == null){
		  return "login" ;
	   }else{
		   
	   }
	return null;
   }
   @RequestMapping(value="error")
   public String error(){
	   return"error";
   }
}