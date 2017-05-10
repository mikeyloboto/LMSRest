package com.gcit.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.library.entity.Borrower;

public class BorrowerDAO extends BaseDAO implements ResultSetExtractor<List<Borrower>>{

	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_borrower (name, address, phone) values (?, ?, ?)",
				new Object[] { borrower.getName(), borrower.getAddress(),
						borrower.getPhone() });
	}

	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		template.update("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(),
						borrower.getPhone(), borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	public void deleteBorrower(Integer borrowerId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_borrower where cardNo = ?", new Object[] { borrowerId });
	}

	public List<Borrower> readAllBorrowers(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_borrower"), this);
	}

	public Borrower readBorrowerByID(Integer borrowerID) throws ClassNotFoundException, SQLException {
		List<Borrower> borrowers = template.query("select * from tbl_borrower where cardNo = ?",
				new Object[] { borrowerID }, this);
		if (borrowers != null && !borrowers.isEmpty()) {
			return borrowers.get(0);
		}
		return null;
	}

	public List<Borrower> readBorrowersByName(String borrowerName, Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_borrower where name like ?"), new Object[] { borrowerName }, this);
	}

	public Integer readBorrowersCountByName(String string) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) from tbl_borrower where name like ?", new Object[] { string }, Integer.class);
	}


	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			borrowers.add(a);
		}
		return borrowers;
	}

	public Integer getBorrowerCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_borrower", Integer.class);
	}


}
