package cn.edu.zust.iteeio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.edu.zust.iteeio.model.Order;
import cn.edu.zust.iteeio.model.OrderBook;
import cn.edu.zust.iteeio.model.OrderManage;
/**
 * Created by Eliasu T. (伊拉索)
 */
public class OrderManageRowMapper implements RowMapper<OrderManage> {

	@Override
	public OrderManage mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderManage om = new OrderManage();
		Order order = new Order();
		OrderBook ob = new OrderBook();
		order.setAddress(rs.getString("address"));
		order.setCardNumber(rs.getString("cardNumber"));
		order.setCity(rs.getString("city"));
		order.setCountry(rs.getString("country"));
		order.setEmail(rs.getString("email"));
		order.setName(rs.getString("name"));
		order.setOrderId(rs.getLong("orderid"));
		order.setPaymentStatus(rs.getString("paymentstatus"));
		order.setPhone(rs.getString("phone"));
		order.setProvince(rs.getString("province"));
		order.setShipmentStatus(rs.getString("shipmentstatus"));
		
		ob.setBookId(rs.getLong("bookid"));
		ob.setOrderId(rs.getLong("orderid"));
		ob.setTotalPrice(rs.getDouble("totalprice"));
		ob.setTotalQty(rs.getLong("totalQuantity"));
		
		om.setOrder(order);
		om.setOrderBook(ob);
		return om;
	}

}
