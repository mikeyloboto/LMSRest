package com.gcit.library.service;

import java.sql.SQLException;
import java.util.List;
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
import com.gcit.library.entity.Borrower;
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
	@RequestMapping(value = "/closeLoan", method = RequestMethod.POST, consumes="application/json")
	public void closeLoan(Integer newBranch, Loan g) throws SQLException {
		try {
			Branch branch = new Branch();
			branch.setBranchNo(newBranch);
			ldao.closeLoan(g);
			cdao.incrementCopies(branch, g.getBook(), 1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getAllBooksInBranch/{pageNo}", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public Map<Book, Integer> getAllBooksInBranch(Branch branch, @PathVariable Integer pageNo) throws SQLException {
		try {
			return cdao.readCopiesFirstLevel(branch, pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/incrementCopies", method = RequestMethod.POST, consumes="application/json")
	public void incrementCopies(Branch br, Book book, Integer increment) throws SQLException {
		try {
			cdao.incrementCopies(br, book, increment);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/startLoan", method = RequestMethod.POST, consumes="application/json")
	public void startLoan(Loan loan) throws SQLException {
		try {
			ldao.addLoanBase(loan);
			cdao.incrementCopies(loan.getBranch(), loan.getBook(), -1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
