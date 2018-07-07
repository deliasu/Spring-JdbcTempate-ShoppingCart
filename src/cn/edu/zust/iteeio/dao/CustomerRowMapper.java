package cn.edu.zust.iteeio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.edu.zust.iteeio.model.Customer;
/**
 * Created by Eliasu T. (伊拉索)
 */
public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer cust = new Customer(); 
		cust.setCity(rs.getString("city"));
		cust.setId(rs.getLong("custid"));
		cust.setEmail(rs.getString("email"));
		cust.setPhone(rs.getString("phone"));
		cust.setPassword(rs.getString("password"));
		cust.setName(rs.getString("name"));
		cust.setNationality(rs.getString("nationality"));
		return cust;
	}
}
