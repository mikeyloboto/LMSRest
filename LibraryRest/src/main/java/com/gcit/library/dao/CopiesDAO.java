package com.gcit.library.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.gcit.library.entity.Book;
import com.gcit.library.entity.Branch;

public class CopiesDAO extends BaseDAO {

	@Autowired
	BookDAO bdao;

	public Map<Book, Integer> readCopiesFirstLevel(Integer branchNo, Integer pageNo)
			throws SQLException, ClassNotFoundException {
		setPageNo(pageNo);
		Map<Book, Integer> map = new HashMap<>();
		List<Book> books = bdao.readBookListInBranch(branchNo, null);
		for (Book b : books) {
			Integer c = bdao.getBookCopies(b, branchNo);
			map.put(b, c);
		}
		return map;
	}

	@Deprecated
	public Map<Book, Integer> readCopiesFirstLevelNotZero(Branch branch, Integer pageNo) throws SQLException {
		return null;
	}

	public Boolean modCopies(Integer branchNo, Integer bookId, Integer copies) throws ClassNotFoundException, SQLException {
		Integer bookExistence = template.queryForObject(
				"select count(*) from tbl_book_copies where branchId = ? and bookId = ?",
				new Object[] { branchNo, bookId }, Integer.class);
		if (bookExistence == 0) {
			// add fresh
			template.update("insert into tbl_book_copies (branchId, bookId, noOfCopies) values (?, ?, ?)",
					new Object[] { branchNo, bookId, copies });
		} else {
			// update
			template.update("update tbl_book_copies set noOfCopies = ? where branchId = ? and bookId = ?",
					new Object[] { copies, branchNo, bookId });
		}
		return Boolean.TRUE;
	}

	public Boolean incrementCopies(Integer branch, Integer book, Integer increment)
			throws ClassNotFoundException, SQLException {
		Integer bookExistence = template.queryForObject(
				"select count(*) from tbl_book_copies where branchId = ? and bookId = ?",
				new Object[] { branch, book }, Integer.class);
		if (bookExistence == 0) {
			// add fresh
			template.update("insert into tbl_book_copies (branchId, bookId, noOfCopies) values (?, ?, ?)",
					new Object[] { branch, book, increment });
		} else {
			Integer copies = template.queryForObject(
					"select noOfCopies from tbl_book_copies where branchId = ? and bookId = ?",
					new Object[] { branch, book }, Integer.class) + increment;
			// update
			template.update("update tbl_book_copies set noOfCopies = ? where branchId = ? and bookId = ?",
					new Object[] { copies, branch, book });
		}
		return Boolean.TRUE;
	}
}
