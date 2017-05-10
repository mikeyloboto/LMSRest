package com.gcit.library.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.library.dao.AuthorDAO;
import com.gcit.library.dao.BookDAO;
import com.gcit.library.dao.BorrowerDAO;
import com.gcit.library.dao.BranchDAO;
import com.gcit.library.dao.CopiesDAO;
import com.gcit.library.dao.GenreDAO;
import com.gcit.library.dao.LoanDAO;
import com.gcit.library.dao.PublisherDAO;
import com.gcit.library.entity.Book;
import com.gcit.library.entity.Branch;

@RestController
public class LibrarianService {
	
	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;

	@Autowired
	BorrowerDAO bordao;

	@Autowired
	BranchDAO brdao;

	@Autowired
	CopiesDAO cdao;

	@Autowired
	GenreDAO gdao;

	@Autowired
	LoanDAO ldao;

	@Autowired
	PublisherDAO pdao;

	@RequestMapping(value = "/getAllBooksInBranch/{pageNo}", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Map<Book, Integer> getAllBooksInBranch(Branch branch, @PathVariable Integer pageNo) throws SQLException {
		try {
			return cdao.readCopiesFirstLevel(branch, pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBookCountInBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getBookCountInBranch(Book book, Branch branch) {
		Integer toRet = 0;
		try {
			toRet = getAllBooksInBranch(branch, null).get(book);
			if (toRet == null)
				toRet = 0;
			return toRet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBookCountInBranch", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Integer getBookCountInBranch(Branch branch) throws SQLException {
		try {
			return bdao.readBookCopiesCountInBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/incrementBookCopiesInBranch", method = RequestMethod.POST, consumes="application/json")
	public void incrementBookCopiesInBranch(Branch br, Book book, Integer increment) throws SQLException {
		try {
			cdao.incrementCopies(br, book, increment);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/updateBookCopiesInBranch", method = RequestMethod.POST, consumes="application/json")
	public void updateBookCopiesInBranch(Branch br, Book book, Integer copies) throws SQLException {
		try {
			cdao.modCopies(br, book, copies);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
