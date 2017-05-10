package com.gcit.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.library.entity.Publisher;

public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>> {

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone() });
	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update(
				"update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });
	}

	public void deletePublisher(Integer publisherId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_publisher where publisherId = ?", new Object[] { publisherId });
	}

	public List<Publisher> readAllPublishers(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_publisher"), this);
	}

	public Publisher readPublisherByID(Integer PublisherID) throws ClassNotFoundException, SQLException {
		List<Publisher> publishers = template.query("select * from tbl_publisher where publisherId = ?",
				new Object[] { PublisherID }, this);
		if (publishers != null && !publishers.isEmpty()) {
			return publishers.get(0);
		}
		return null;
	}

	public List<Publisher> readPublishersByName(String PublisherName, Integer pageNo)
			throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_publisher where publisherName like ?"), new Object[] { PublisherName },
				this);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(a);
		}
		return publishers;
	}

	public Integer getPublisherCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_publisher", Integer.class);
	}

	public Integer readPublishersCountByName(String publisherName) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) from tbl_publisher where publisherName like ?", new Object[] { publisherName }, Integer.class);
	}

	public Publisher readPublisherByBookId(Integer bookId) {
		return template.query("select * from tbl_publisher where publisherId in (select pubId as publisherId from tbl_book where bookId = ?)", new Object[]{bookId}, this).get(0);
	}

}
