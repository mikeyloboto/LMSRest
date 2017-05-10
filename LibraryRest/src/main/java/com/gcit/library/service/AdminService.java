package com.gcit.library.service;

import java.sql.SQLException;
import java.util.List;

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
import com.gcit.library.entity.Author;
import com.gcit.library.entity.Book;
import com.gcit.library.entity.Borrower;
import com.gcit.library.entity.Branch;
import com.gcit.library.entity.Genre;
import com.gcit.library.entity.Loan;
import com.gcit.library.entity.Publisher;

public class AdminService {

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
	public void addAuthor(Author author) throws SQLException {
		try {
			adao.addAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void addBook(Book book) throws SQLException {
		try {
			Integer bookId = bdao.addBookWithID(book);
			if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
				for (Author a : book.getAuthors()) {
					bdao.addBookAuthors(bookId, a.getAuthorId());
				}
			}
			if (book.getGenres() != null && !book.getGenres().isEmpty()) {
				for (Genre g : book.getGenres()) {
					gdao.addBookGenre(g, bookId);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void addGenre(Genre g) throws SQLException {
		try {
			gdao.addGenre(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void addPublisher(Publisher p) throws SQLException {
		try {
			pdao.addPublisher(p);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void addBranch(Branch b) throws SQLException {
		try {
			brdao.addBranch(b);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void addBorrower(Borrower g) throws SQLException {
		try {
			bordao.addBorrower(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void addLoan(Loan g) throws SQLException {
		try {
			ldao.addLoanBase(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public List<Author> getAllAuthors(Integer pageNo) throws SQLException {
		try {
			return adao.readAllAuthors(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Book> getAllBooks(Integer pageNo) throws SQLException {
		try {
			return processAllBooks(bdao.readAllBooks(pageNo));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Genre> getAllGenres(Integer pageNo) throws SQLException {
		try {
			return gdao.readAllGenres(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Publisher> getAllPublishers(Integer pageNo) throws SQLException {
		try {
			return pdao.readAllPublishers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

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
	public List<Borrower> getAllBorrowers(Integer pageNo) throws SQLException {
		try {
			return bordao.readAllBorrowers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Loan> getAllLoans(Integer pageNo) throws SQLException {
		try {
			return ldao.readAllLoans(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Book getBookFromID(Integer id) throws SQLException {
		try {
			return processBook(bdao.readBookFromId(id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Author getAuthorFromID(Integer id) throws SQLException {
		try {
			return adao.readAuthorByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Genre getGenreFromID(Integer id) throws SQLException {
		try {
			return gdao.readGenreByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Publisher getPublisherFromID(Integer id) throws SQLException {
		try {
			return pdao.readPublisherByID(id);
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
	public Borrower getBorrowerFromID(Integer id) throws SQLException {
		try {
			return bordao.readBorrowerByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Book> getBooksFromName(Integer pageNo, String searchString) throws SQLException {
		try {
			return processAllBooks(bdao.readBookFromName("%" + searchString + "%", pageNo));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Author> getAuthorsFromName(Integer pageNo, String searchString) throws SQLException {
		try {
			return adao.readAuthorsByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Genre> getGenresFromName(Integer pageNo, String searchString) throws SQLException {
		try {
			return gdao.readGenresByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Publisher> getPublishersFromName(int pageNo, String searchString) throws SQLException {
		try {
			return pdao.readPublishersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Branch> getBranchesFromName(Integer pageNo, String searchString) throws SQLException {
		try {
			return brdao.readBranchesByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Borrower> getBorrowersFromName(Integer pageNo, String searchString) throws SQLException {
		try {
			return bordao.readBorrowersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Loan expandLoan(Loan loan) throws SQLException {
		try {
			return ldao.expandLoan(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	@Transactional
	public Integer getBookCount() throws SQLException {
		try {
			return bdao.readBookCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getAuthorCount() throws SQLException {
		try {
			return adao.readAuthorCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getGenreCount() throws SQLException {
		try {
			return gdao.readGenreCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getPublisherCount() throws SQLException {
		try {
			return pdao.getPublisherCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getBranchCount() throws SQLException {
		try {
			return brdao.readBranchCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getBorrowerCount() throws SQLException {
		try {
			return bordao.getBorrowerCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getLoanCount() throws SQLException {
		try {
			return ldao.getLoanCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getBooksFromNameCount(String searchString) throws SQLException {
		try {
			return bdao.readBookCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getAuthorsFromNameCount(String searchString) throws SQLException {
		try {
			return adao.readAuthorsCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getGenresFromNameCount(String searchString) throws SQLException {
		try {
			return gdao.readGenresCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getPublishersFromNameCount(String searchString) throws SQLException {
		try {
			return pdao.readPublishersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getBranchesFromNameCount(String searchString) throws SQLException {
		try {
			return brdao.readBranchesCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public Integer getBorrowersFromNameCount(String searchString) throws SQLException {
		try {
			return bordao.readBorrowersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	public void removeBook(Integer bookId) throws SQLException {
		try {
			bdao.deleteBook(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removeAuthor(Integer authorId) throws SQLException {
		try {
			adao.deleteAuthor(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removeGenre(Integer genreId) throws SQLException {
		try {
			gdao.deleteGenre(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removePublisher(Integer publisherId) throws SQLException {
		try {
			pdao.deletePublisher(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removeBranch(Integer branchId) throws SQLException {
		try {
			brdao.deleteBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void removeBorrower(Integer borrowerId) throws SQLException {
		try {
			bordao.deleteBorrower(borrowerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modBook(Book book) throws SQLException {
		try {
			bdao.updateBookPublisher(book);
			bdao.removeBookAuthors(book.getBookId());
			if (book.getAuthors() != null && !book.getAuthors().isEmpty()) {
				for (Author a : book.getAuthors()) {
					bdao.addBookAuthors(book.getBookId(), a.getAuthorId());
				}
			}
			gdao.removeBookGenres(book.getBookId());
			if (book.getGenres() != null && !book.getGenres().isEmpty()) {
				for (Genre g : book.getGenres()) {
					gdao.addBookGenre(g, book.getBookId());
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modAuthor(Author author) throws SQLException {
		try {
			adao.updateAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modGenre(Genre g) throws SQLException {
		try {
			gdao.updateGenre(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modPublisher(Publisher p) throws SQLException {
		try {
			pdao.updatePublisher(p);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modBranch(Branch g) throws SQLException {
		try {
			brdao.updateBranch(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modBorrower(Borrower g) throws SQLException {
		try {
			bordao.updateBorrower(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void modLoan(Loan g) throws SQLException {
		try {
			ldao.updateLoan(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<Book> processAllBooks(List<Book> books) {
		for (Book b : books) {
			processBook(b);
		}
		return books;
	}
	
	private Book processBook(Book b) {
		b.setAuthors(adao.readAllAuthorsForBook(b.getBookId()));
		b.setPublisher(pdao.readPublisherByBookId(b.getBookId()));
		b.setGenres(gdao.readAllGenresForBook(b.getBookId()));
		return b;
	}
}
