package com.gcit.library.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Branch> getAllBranches(Integer pageNo) throws SQLException {
		try {
			return brdao.readAllBranches(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Borrower getBorrowerFromID(Integer cardNo) throws SQLException {
		try {
			return bordao.readBorrowerByID(cardNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Branch getBranchFromID(Integer id) throws SQLException {
		try {
			return brdao.readBranchByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public Map<Book, Integer> getAllBooksInBranch(Branch branch, Integer pageNo) throws SQLException {
		try {
			return cdao.readCopiesFirstLevel(branch, pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Book getBookFromID(Integer id) throws SQLException {
		try {
			return bdao.readBookFromId(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getLoanCount(Borrower borrower) throws SQLException {
		try {
			return ldao.getLoanCountByID(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Loan> getAllLoans(Borrower borrower, Integer pageNo) throws SQLException {
		try {
			return ldao.readLoansByCardNo(borrower.getCardNo(), pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void incrementCopies(Branch br, Book book, Integer increment) throws SQLException {
		try {
			cdao.incrementCopies(br, book, increment);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
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

	@Transactional
	public void startLoan(Loan loan) throws SQLException {
		try {
			ldao.addLoanBase(loan);
			cdao.incrementCopies(loan.getBranch(), loan.getBook(), -1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
