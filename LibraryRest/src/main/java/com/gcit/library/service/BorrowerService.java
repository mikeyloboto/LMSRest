package com.gcit.library.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.gcit.library.entity.Loan;

@RestController
public class BorrowerService {

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

	@Transactional
	@RequestMapping(value = "/loans/close/{branchNo}", method = RequestMethod.POST, consumes = "application/json")
	public void closeLoan(@PathVariable Integer branchNo, @RequestBody Loan loan) throws SQLException {
		try {
			ldao.closeLoan(loan);
			cdao.incrementCopies(branchNo, loan.getBook().getBookId(), 1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/books/branch/{branchNo}", method = RequestMethod.GET, produces = "application/json")
	public Map<Book, Integer> getAllBooksInBranch(@PathVariable Integer branchNo) throws SQLException {
		try {
			return cdao.readCopiesFirstLevel(branchNo, null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/books/branch/{branchNo}/available", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAvailableBooksInBranch(@PathVariable Integer branchNo) throws SQLException {
		try {
			Map<Book, Integer> map = cdao.readCopiesFirstLevel(branchNo, null);
			List<Book> books = new ArrayList<>(map.keySet());
			for (Book b : books) {
				processBook(b);
			}
			return books;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/books/branch/{branchNo}/{bookId}", method = RequestMethod.PUT, consumes = "application/json")
	public void incrementCopies(@PathVariable Integer branchNo, @PathVariable Integer bookId,
			@RequestBody Integer increment) throws SQLException {
		try {
			cdao.incrementCopies(branchNo, bookId, increment);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping(value = "/loans/client/{cardNo}", method = RequestMethod.GET)
	public List<Loan> getLoansByCardNo(@PathVariable Integer cardNo) throws SQLException {
		try {
			List<Loan> loans = ldao.readLoansByCardNo(cardNo, null);
			for (Loan l : loans) {
				processBook(l.getBook());
			}
			return loans;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/loans/start", method = RequestMethod.POST, consumes = "application/json")
	public void startLoan(@RequestBody Loan loan) throws SQLException {
		try {
			ldao.addLoanBase(loan);
			cdao.incrementCopies(loan.getBranch().getBranchNo(), loan.getBook().getBookId(), -1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/int/{inp}", method = RequestMethod.GET, produces = "application/json")
	public Integer intTest(@PathVariable Integer inp) throws SQLException {
		return inp;
	}
	
	private Book processBook(Book b) {
		b.setAuthors(adao.readAllAuthorsForBook(b.getBookId()));
		b.setPublisher(pdao.readPublisherByBookId(b.getBookId()));
		b.setGenres(gdao.readAllGenresForBook(b.getBookId()));
		return b;
	}
}
