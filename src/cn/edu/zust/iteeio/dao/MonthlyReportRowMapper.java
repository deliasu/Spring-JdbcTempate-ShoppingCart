package cn.edu.zust.iteeio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.edu.zust.iteeio.model.Book;
import cn.edu.zust.iteeio.model.MonthlyReport;
import cn.edu.zust.iteeio.model.Order;
import cn.edu.zust.iteeio.model.OrderBook;
import cn.edu.zust.iteeio.model.OrderManage;

/**
 * Created by Eliasu
 */

public class MonthlyReportRowMapper implements RowMapper<MonthlyReport> {
	/**
	 *
	 * @param rs
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 */
	@Override
	public MonthlyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book bk = new Book();
		bk.setId(rs.getLong("id"));
		bk.setPrice(rs.getDouble("price"));
		OrderManage om = new OrderManage();
		Order order = new Order();
		OrderBook ob = new OrderBook();
		order.setOrderId(rs.getLong("orderid"));
		ob.setOrderId(rs.getLong("orderid"));
		ob.setTotalPrice(rs.getDouble("totalprice"));
		ob.setTotalQty(rs.getLong("totalQuantity"));
		
		om.setOrder(order);
		om.setOrderBook(ob);
		
	    MonthlyReport m = new MonthlyReport();
	    m.setProfit(rs.getDouble("profit"));
	    m.setTotalCost(rs.getDouble("totalCost"));
	    m.setBook(bk);
	    m.setOrderManage(om);
		return m;
	}

}