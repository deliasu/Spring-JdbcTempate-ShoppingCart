package cn.edu.zust.iteeio.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zust.iteeio.dao.BookDaoImpl;
import cn.edu.zust.iteeio.model.Book;
import cn.edu.zust.iteeio.model.MonthlyReport;
import cn.edu.zust.iteeio.model.OrderManage;

/**
 * Created by Eliasu T. (伊拉索) on 2016/12/11
 */

@Controller
public class BookManagementController {
	 private static final String UPLOAD_DIRECTORY ="/resources/images";  
	 public static Book addBookInfo = null;

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/manage")
	public String manageBook(Model model){
		model.addAttribute("title", "ETBook manage");
		return "manage";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add-book-form")
	public String addBookForm(Model model){
		model.addAttribute("title", "ETBook add book");
		return "addBook";
	}

	/**
	 *
	 * @param book
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add-book", method = RequestMethod.POST)
	public ModelAndView addBook(Book book, HttpServletRequest request, HttpSession session, Model model){
		String pubDate = request.getParameter("pubDate");
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String stock = request.getParameter("stock");
		String price = request.getParameter("price");
		String cost = request.getParameter("cost");
		String publisher = request.getParameter("publisher");
		String briefDesc = request.getParameter("briefDesc");
		String detailedDesc = request.getParameter("detailedDesc");
		String author = request.getParameter("author");
		String edition = request.getParameter("edition");
		String language = request.getParameter("language");
		String category = request.getParameter("category");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		book.setAuthor(author);
		book.setTitle(title);
		book.setLanguage(language);
		book.setEdition(edition);
		book.setIsbn(isbn);
		book.setBriefDesc(briefDesc);
		book.setDetailedDesc(detailedDesc);
		book.setPublisher(publisher);
		book.setCategory(category); 
		try {
			book.setPubDate(format.parse(pubDate));
			book.setPrice(Double.parseDouble(price));
			book.setStock(Integer.parseInt(stock));
			book.setCost(Double.parseDouble(cost));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}catch(Exception e){
			
		}
		
		if(addBookInfo == null){
			addBookInfo = new Book();
		}
		if(!session.isNew()){
			session.getAttribute("addBookInfo");
		}
		addBookInfo = book;
		session.setAttribute("addBookInfo", addBookInfo);
		model.addAttribute("title", "ETBook image");
		return new ModelAndView("bookImage");
	}

	/**
	 *
	 * @param image
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add-book-image-and-save", method=RequestMethod.POST)
	public ModelAndView addBookImage( @RequestParam CommonsMultipartFile image,  HttpSession session, Model model)throws Exception{
		boolean uploaded = true;
		ServletContext context = session.getServletContext();  
		String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    String filename = image.getOriginalFilename();  
	    System.out.println(filename);        
	    byte[] bytes = image.getBytes();  
	    BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + filename)));
		} catch (FileNotFoundException e) {
			uploaded= false;
			e.printStackTrace();
		}
	    try {
			stream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
			uploaded = false;
		}  
	    try {
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			uploaded = false;
		}  
	    try {
			stream.close();
		} catch (IOException e) {
			uploaded = false;
			e.printStackTrace();
		} 
	    if(uploaded){
	    	Book book = new Book();
	    	book = (Book) session.getAttribute("addBookInfo");
	    	if(book != null){
	    	    book.setImage(filename);
	    	    BookDaoImpl bookDaoImpl = new BookDaoImpl();
	    	    bookDaoImpl.addBook(book);
	    	    
	    	}
	    	model.addAttribute("title", "add book");
	    	return new ModelAndView("redirect:add-book-form");
	    }else{
	    	return new ModelAndView("error");
	    }
		
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modify-book")
	public ModelAndView modifyBook(Model model){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		List<Book> allbook = bookDaoImpl.displayAllBooks();
		model.addAttribute("allbooks", allbook);
		model.addAttribute("title", "ETBook modify book");
		return new ModelAndView("modifyBook");
	}

	/**
	 *
	 * @param book
	 * @return
	 */
	@RequestMapping(value = "/edit-book", method=RequestMethod.POST)
	public @ResponseBody  Book modifyBookJson(@RequestBody Book book){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		int row = bookDaoImpl.modifyBook(book);
		if(row == 1){
		   return book;
		}
		return null;
	}
	@RequestMapping(value = "/delete-book")
	public @ResponseBody int deleteBookJson(@RequestParam("ID") long id){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		int deleted = bookDaoImpl.delete(id);
		return deleted;
	}
	@RequestMapping(value="/display-gateway")
	public String sqlGatewayForm(HttpServletRequest request){
		return "gateway";
	}
	@RequestMapping(value ="/manage-order")
	public String manageOrder(Model model){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		List<OrderManage> allOrder = bookDaoImpl.manageAllOrder(); 
		model.addAttribute("order", allOrder);
		model.addAttribute("title", "ETBook mange order");
		return "manageOrder";
	}

	/**
	 *
	 * @param pay
	 * @param ship
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update-pay-ship-status")
	public @ResponseBody String updateOrderStatus(@RequestParam("PAY") String pay, @RequestParam("SHIP") String ship, @RequestParam("ORDERID") long id ){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		pay = pay.trim();System.out.println(pay);
		ship = ship.trim();System.out.println(ship);
		 bookDaoImpl.updateOrderStatus(pay, ship, id);
		return "OK";
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete-customer-order")
	public @ResponseBody String deleteCustomerOrder(@RequestParam("ID") long id){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		bookDaoImpl.deleteCustOrder(id);
		return "OK";
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/monthly-report")
	public String monthlyReport(Model model){
		BookDaoImpl bookDaoImpl = new BookDaoImpl();
		List<MonthlyReport> report = bookDaoImpl.monthlyReport();
		for(int i= 0; i<report.size(); i++){
			System.out.println("Profit" + report.get(i).getProfit());
		}
		model.addAttribute("report", report);
		model.addAttribute("title", "ETBook monthly report");
		return "monthlyReport";
	}
	
}
