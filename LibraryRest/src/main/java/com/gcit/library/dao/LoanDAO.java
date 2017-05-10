package com.gcit.library.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.library.entity.Borrower;
import com.gcit.library.entity.Loan;

public class LoanDAO extends BaseDAO implements ResultSetExtractor<List<Loan>>{

	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	BranchDAO brdao;
	
	@Autowired
	BorrowerDAO bordao;
	
	public void addLoanBase(Loan loan) throws ClassNotFoundException, SQLException {
		LocalDate due = LocalDate.now().plusDays(7);
		Date dueDate = Date.valueOf(due);
		template.update("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dateIn, dueDate) values (?, ?, ?, CURTIME(), NULL, ?)",
				new Object[] { loan.getBook().getBookId(), loan.getBranch().getBranchNo(),
						loan.getBorrower().getCardNo(), dueDate });
	}

	public void updateLoan(Loan loan) throws ClassNotFoundException, SQLException {
		// Workaround for an annoying null pointer
		Date dateIn;
		if (loan.getDateIn() == null)
			dateIn = null;
		else
			dateIn = Date.valueOf(loan.getDateIn());
		
		template.update("update tbl_book_loans set dueDate = ?, dateIn = ? where bookId = ? and branchId = ? and cardNo = ? and dateOut = ?",
				new Object[] { Date.valueOf(loan.getDateDue()), dateIn,
						loan.getBook().getBookId(), loan.getBranch().getBranchNo(), loan.getBorrower().getCardNo(),
						Timestamp.valueOf(loan.getDateOut()) });
	}
	
	public void closeLoan(Loan loan) throws ClassNotFoundException, SQLException {
		template.update("update tbl_book_loans set dateIn = CURDATE() where bookId = ? and branchId = ? and cardNo = ? and dateOut = ?",
				new Object[] {loan.getBook().getBookId(), loan.getBranch().getBranchNo(), loan.getBorrower().getCardNo(),
						Timestamp.valueOf(loan.getDateOut()) });
	}

	public void deleteLoan(Loan loan) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book_loans where bookId = ? and branchNo = ? and cardNo = ? and dateOut = ?",
				new Object[] { loan.getBook().getBookId(), loan.getBranch().getBranchNo(),
						loan.getBorrower().getCardNo(), Timestamp.valueOf(loan.getDateOut()) });
	}

	public List<Loan> readAllLoans(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_book_loans where dateIn is null"), this);
	}

	public List<Loan> readLoansByCardNo(Integer cardNo, Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_book_loans where cardNo = ? and dateIn is null"), new Object[] { cardNo }, this);
	}

	@Override
	public List<Loan> extractData(ResultSet rs) throws SQLException{
		List<Loan> loans = new ArrayList<>();
		while (rs.next()) {
			Loan a = new Loan();
			try {
				a.setBook(bdao.readBookFromId(rs.getInt("bookId")));
				a.setBorrower(bordao.readBorrowerByID(rs.getInt("cardNo")));
				a.setBranch(brdao.readBranchByID(rs.getInt("branchId")));
				a.setDateOut(rs.getTimestamp("dateOut").toLocalDateTime());
				a.setDateDue(rs.getDate("dueDate").toLocalDate());
				a.setDateIn(rs.getDate("dateIn").toLocalDate());
				loans.add(a);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	
		}
		return loans;
	}

	public Integer getLoanCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_book_loans where dateIn IS NULL", Integer.class);
	}
	
	public Integer getLoanCountByID(Borrower borrower) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_book_loans where dateIn IS NULL and cardNo = ?", new Object[]{borrower.getCardNo()}, Integer.class);
	}

	public Loan expandLoan(Loan loan) throws ClassNotFoundException, SQLException {
		return template.query("select * from tbl_book_loans where cardNo = ? and branchId = ? and bookId = ? and dateOut = ?", new Object[] {loan.getBorrower().getCardNo(), loan.getBranch().getBranchNo(), loan.getBook().getBookId(), Timestamp.valueOf(loan.getDateOut())}, this).get(0);
	}

}
