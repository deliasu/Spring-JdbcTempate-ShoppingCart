package cn.edu.zust.iteeio.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.edu.zust.iteeio.model.Book;
import cn.edu.zust.iteeio.model.Customer;
import cn.edu.zust.iteeio.model.MonthlyReport;
import cn.edu.zust.iteeio.model.Order;
import cn.edu.zust.iteeio.model.OrderBook;
import cn.edu.zust.iteeio.model.OrderManage;

/**
 * Created by Eliasu T. Doh(伊拉索)
 */
public class BookDaoImpl implements BookDao {
    ApplicationContext ctx= null;
    JdbcTemplate template = null;
    DataSource dataSource = null;
    
    public BookDaoImpl(){
    	ctx = new ClassPathXmlApplicationContext("datasource-beans.xml");
    	dataSource = ctx.getBean("mySqlDataSource", DataSource.class);
    	template = new JdbcTemplate(dataSource);
    }

	/**
	 *
	 * @return
	 */
	@Override
	public List<Book> displayAllBooks() {
		List<Book> allBooks = template.query("select * from book", new BookRowMapper());
		return allBooks;
	}

	@Override
	public int addBook(Book b) {
		int bookadded = template.update("insert into book(isbn, title, language, publisher, pubdate, briefdesc, detaileddesc, edition, image, cost, price, category, author, stock) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", b.getIsbn(), b.getTitle(), b.getLanguage(), b.getPublisher(), b.getPubDate(), b.getBriefDesc(), b.getDetailedDesc(), b.getEdition(), b.getImage(), b.getCost(), b.getPrice(), b.getCategory(), b.getAuthor(), b.getStock());
	    return bookadded;
	}

	@Override
	public int modifyBook(Book b) {
		int updatedBook = template.update("update book set isbn =?, title =?, language =?, publisher=?, pubdate=?, briefdesc=?, detaileddesc=?, edition=?, image=?, cost=?, price=?, category=?, author=?, stock=? where id=?", b.getIsbn(), b.getTitle(), b.getLanguage(), b.getPublisher(), b.getPubDate(), b.getBriefDesc(), b.getDetailedDesc(), b.getEdition(), b.getImage(), b.getCost(), b.getPrice(), b.getCategory(), b.getAuthor(), b.getStock(), b.getId());
        return updatedBook;
	}
	@Override
	public Book getBookDetail(long id) {
		Book book = new Book();
		try{
			book = template.queryForObject("select * from Book where id = ?", new Object[]{id}, new BookRowMapper());
			return book;
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	@Override
	public List<Book> looseSearchBooks(String chars) {
		List<Book> allLooseSearchBooks = template.query("select * from book where title Like ?" ,new Object[]{"%" + chars + "%"}, new BookRowMapper());
		return allLooseSearchBooks;
	}
	@Override
	public int insertCustomer(Customer c) {
		int custInserted = template.update("insert into customer(email, name, phone, password, nationality, city) values(?, ?, ?, ?, ?, ?)", c.getEmail(), c.getName(), c.getPhone(), c.getPassword(), c.getNationality(), c.getCity());
		return custInserted;
	}
	@Override
	public Customer customerLogin(String email, String password) {
		Customer cust = new Customer();
		try{
			cust =  template.queryForObject("select * from customer where email = ? and password = ?", new Object[]{email, password}, new CustomerRowMapper());
		}catch(EmptyResultDataAccessException e){
			cust = null;
		}
		return cust;
	}
	@Override
	public boolean isRegisteredCustomer(String email) {
		int count = template.queryForObject("select count(*) from customer where email = ?", new Object[]{email}, Integer.class);
		if(count >= 1){
			return true;
		}
		return false;
	}
	@Override
	public int delete(long id) {
		int deleted = template.update("delete from book where id=?", id);
		return deleted; 
	}
	@Override
	public int insertOrder(Order o) {
		int order = template.update("insert into orders(orderid, name, cardNumber, phone, email, country, province, city, address, orderdate) values(?, ?, ?, ?, ?, ?, ?, ?, ?, curdate())", o.getOrderId(),o.getName(), o.getCardNumber(), o.getPhone(), o.getEmail(), o.getCountry(), o.getProvince(), o.getCity(), o.getAddress());
		return order;
	}
	@Override
	public int insertOrderedBook(OrderBook ob) {
		int orderbook = template.update("insert into orderbook(orderid, bookid, totalquantity, totalprice) values(?, ?, ?, ?)", ob.getOrderId(), ob.getBookId(), ob.getTotalQty(), ob.getTotalPrice());
		return orderbook;
	}
   public long getMaxOrderId(){
	   long id = template.queryForObject("select IFNULL(max(orderid), 0) + 1 from orders", Long.class);
       return id;
   }
   public int updatePaid(int paid, long id){
	   int num = template.update("update book set paid = ? where id=?", paid, id);
	   return num;
   }
@Override
public List<OrderManage> manageAllOrder() {
	List<OrderManage> allOrder = template.query("select * from orderbook natural join orders", new OrderManageRowMapper());
	return allOrder;
}
@Override
public int updateOrderStatus(String pay, String ship, long id) {
	int updatedStatus = template.update("update orders set paymentstatus =? , shipmentstatus =? where orderid = ?", pay, ship, id);
	return updatedStatus;
}
@Override
public int deleteCustOrder(long id) {
	int deletedOrder = template.update("delete from orders where orderid = ?", id) ;
	deletedOrder = template.update("delete from orderbook where orderid = ?", id);
	return deletedOrder;
}
public List<MonthlyReport>  monthlyReport(){
	List<MonthlyReport> report = template.query("select book.id, book.price, orderbook.orderid, orderbook.totalquantity, book.cost * orderbook.totalquantity totalCost, orderbook.totalprice, sum(orderbook.totalprice -(book.cost * orderbook.totalquantity)) profit from book, orders, orderbook where book.id= orderbook.bookid and orderbook.orderid = orders.orderid order by month(orders.orderdate)", new MonthlyReportRowMapper());
    System.out.println(report);
	return report;
}
}
