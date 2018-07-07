package cn.edu.zust.iteeio.dao;

import java.util.List;

import cn.edu.zust.iteeio.model.Book;
import cn.edu.zust.iteeio.model.Customer;
import cn.edu.zust.iteeio.model.Order;
import cn.edu.zust.iteeio.model.OrderBook;
import cn.edu.zust.iteeio.model.OrderManage;
/**
 * Created by Eliasu T. (伊拉索)
 */
public interface BookDao {
	public List<Book> displayAllBooks();
	public int addBook(Book b);
	public int modifyBook(Book b);
	public Book getBookDetail(long id);
	public List<Book> looseSearchBooks(String chars);
	public int insertCustomer(Customer c);
	public boolean isRegisteredCustomer(String email);
	public Customer customerLogin(String email, String password);
	public int delete(long id);
	public int insertOrder(Order o);
	public int insertOrderedBook(OrderBook ob);
	public List<OrderManage> manageAllOrder();
	public int updateOrderStatus(String pay, String ship, long id);
	public int deleteCustOrder(long id);
}
