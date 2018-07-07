package cn.edu.zust.iteeio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cn.edu.zust.iteeio.model.Book;

/**
 * Created by Eliasu T. (伊拉索)
 */
public class BookRowMapper implements RowMapper<Book> {
	/**
	 *
	 * @param rs
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book bk = new Book();
		bk.setId(rs.getLong("id"));
		bk.setIsbn(rs.getString("isbn"));
		bk.setTitle(rs.getString("title"));
		bk.setLanguage(rs.getString("language"));
		bk.setPublisher(rs.getString("publisher"));
		bk.setPubDate(rs.getDate("pubdate"));
		bk.setBriefDesc(rs.getString("briefdesc"));
		bk.setDetailedDesc(rs.getString("detaileddesc"));
		bk.setEdition(rs.getString("edition"));
		bk.setImage(rs.getString("image"));
		bk.setCost(rs.getDouble("cost"));
		bk.setPrice(rs.getDouble("price"));
		bk.setCategory(rs.getString("category"));
		bk.setPaid(rs.getInt("paid"));
		bk.setStock(rs.getInt("stock"));
		bk.setAuthor(rs.getString("author"));
		return bk;
	}
	

}
