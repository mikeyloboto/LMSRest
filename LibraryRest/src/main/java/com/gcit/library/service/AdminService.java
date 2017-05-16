package com.gcit.library.service;

import java.sql.SQLException;
import java.util.List;

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
import com.gcit.library.entity.Author;
import com.gcit.library.entity.Book;
import com.gcit.library.entity.Borrower;
import com.gcit.library.entity.Branch;
import com.gcit.library.entity.Genre;
import com.gcit.library.entity.Loan;
import com.gcit.library.entity.Publisher;

@RestController
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

	// @Transactional
	// @RequestMapping(value = "/author", method = RequestMethod.PUT,
	// consumes="application/json")
	// public void addAuthor(@RequestBody Author author) throws SQLException {
	// try {
	// adao.addAuthor(author);
	// } catch (ClassNotFoundException | SQLException e) {
	// e.printStackTrace();
	// }
	// }

	@Transactional
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes = "application/json")
	public void addBook(@RequestBody Book book) throws SQLException {
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
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void addBorrower(@RequestBody Borrower g) throws SQLException {
		try {
			bordao.addBorrower(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST, consumes = "application/json")
	public void addBranch(@RequestBody Branch b) throws SQLException {
		try {
			brdao.addBranch(b);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST, consumes = "application/json")
	public void addGenre(@RequestBody Genre g) throws SQLException {
		try {
			gdao.addGenre(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addLoan", method = RequestMethod.POST, consumes = "application/json")
	public void addLoan(@RequestBody Loan g) throws SQLException {
		try {
			ldao.addLoanBase(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, consumes = "application/json")
	public void addPublisher(@RequestBody Publisher p) throws SQLException {
		try {
			pdao.addPublisher(p);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/expandLoan", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Loan expandLoan(@RequestBody Loan loan) throws SQLException {
		try {
			return ldao.expandLoan(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/all", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthors() throws SQLException {
		try {
			return adao.readAllAuthors(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthorsPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return adao.readAllAuthors(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllBooks/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooks(@PathVariable Integer pageNo) throws SQLException {
		try {
			return processAllBooks(bdao.readAllBooks(pageNo));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllBorrowers/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowers(@PathVariable Integer pageNo) throws SQLException {
		try {
			return bordao.readAllBorrowers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllBranches/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranches(@PathVariable Integer pageNo) throws SQLException {
		try {
			return brdao.readAllBranches(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllGenres/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getAllGenres(@PathVariable Integer pageNo) throws SQLException {
		try {
			return gdao.readAllGenres(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllLoans/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getAllLoans(@PathVariable Integer pageNo) throws SQLException {
		try {
			return ldao.readAllLoans(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getAllPublishers/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getAllPublishers(@PathVariable Integer pageNo) throws SQLException {
		try {
			return pdao.readAllPublishers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getAuthorCount() throws SQLException {
		try {
			return adao.readAuthorCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = "application/json")
	public Author getAuthorFromID(@PathVariable Integer id) throws SQLException {
		try {
			return adao.readAuthorByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return adao.readAuthorsByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsFromName(@PathVariable String searchString) throws SQLException {
		try {
			return adao.readAuthorsByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/author/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getAuthorsFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return adao.readAuthorsCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBookCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getBookCount() throws SQLException {
		try {
			return bdao.readBookCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBookFromId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Book getBookFromID(@PathVariable Integer id) throws SQLException {
		try {
			return processBook(bdao.readBookFromId(id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBooksFromName/{pageNo}/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksFromName(@PathVariable Integer pageNo, @PathVariable String searchString)
			throws SQLException {
		try {
			return processAllBooks(bdao.readBookFromName("%" + searchString + "%", pageNo));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBookFromNameCount/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public Integer getBooksFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return bdao.readBookCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBorrowerCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getBorrowerCount() throws SQLException {
		try {
			return bordao.getBorrowerCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBorrowerFromId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Borrower getBorrowerFromID(@PathVariable Integer id) throws SQLException {
		try {
			return bordao.readBorrowerByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBorrowersFromName/{pageNo}/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersFromName(@PathVariable Integer pageNo, @PathVariable String searchString)
			throws SQLException {
		try {
			return bordao.readBorrowersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBorrowersFromNameCount/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public Integer getBorrowersFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return bordao.readBorrowersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBranchCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getBranchCount() throws SQLException {
		try {
			return brdao.readBranchCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBranchesFromName/{pageNo}/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesFromName(@PathVariable Integer pageNo, @PathVariable String searchString)
			throws SQLException {
		try {
			return brdao.readBranchesByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBranchesFromNameCount/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public Integer getBranchesFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return brdao.readBranchesCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getBranchFromId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Branch getBranchFromID(@PathVariable Integer id) throws SQLException {
		try {
			return brdao.readBranchByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getGenreCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getGenreCount() throws SQLException {
		try {
			return gdao.readGenreCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getGenreFromId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Genre getGenreFromID(@PathVariable Integer id) throws SQLException {
		try {
			return gdao.readGenreByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getGenresFromName/{pageNo}/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenresFromName(@PathVariable Integer pageNo, @PathVariable String searchString)
			throws SQLException {
		try {
			return gdao.readGenresByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getGenresFromNameCount/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public Integer getGenresFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return gdao.readGenresCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getLoanCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getLoanCount() throws SQLException {
		try {
			return ldao.getLoanCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublisherCount", method = RequestMethod.GET, produces = "application/json")
	public Integer getPublisherCount() throws SQLException {
		try {
			return pdao.getPublisherCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublisherFromId/{id}", method = RequestMethod.GET, produces = "application/json")
	public Publisher getPublisherFromID(@PathVariable Integer id) throws SQLException {
		try {
			return pdao.readPublisherByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublishersFromName/{pageNo}/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishersFromName(@PathVariable Integer pageNo, @PathVariable String searchString)
			throws SQLException {
		try {
			return pdao.readPublishersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/getPublisherFromNameCount/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public Integer getPublishersFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return pdao.readPublishersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/author", method = RequestMethod.PUT, consumes = "application/json")
	public void putAuthor(@RequestBody Author author) throws SQLException {
		try {
			Author temp = adao.readAuthorByID(author.getAuthorId());
			if (temp != null)
				adao.updateAuthor(author);
			else
				adao.addAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/modBook", method = RequestMethod.POST, consumes = "application/json")
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
	@RequestMapping(value = "/modBorrower", method = RequestMethod.POST, consumes = "application/json")
	public void modBorrower(Borrower borrower) throws SQLException {
		try {
			bordao.updateBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/modBranch", method = RequestMethod.POST, consumes = "application/json")
	public void modBranch(Branch branch) throws SQLException {
		try {
			brdao.updateBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/modGenre", method = RequestMethod.POST, consumes = "application/json")
	public void modGenre(Genre genre) throws SQLException {
		try {
			gdao.updateGenre(genre);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/modLoan", method = RequestMethod.POST, consumes = "application/json")
	public void modLoan(Loan loan) throws SQLException {
		try {
			ldao.updateLoan(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/modPublisher", method = RequestMethod.POST, consumes = "application/json")
	public void modPublisher(Publisher publisher) throws SQLException {
		try {
			pdao.updatePublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/author/{authorId}", method = RequestMethod.DELETE)
	public void removeAuthor(@PathVariable Integer authorId) throws SQLException {
		try {
			adao.deleteAuthor(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/removeBook/{bookId}", method = RequestMethod.GET)
	public void removeBook(@PathVariable Integer bookId) throws SQLException {
		try {
			bdao.deleteBook(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/removeBorrower/{borrowerId}", method = RequestMethod.GET)
	public void removeBorrower(@PathVariable Integer borrowerId) throws SQLException {
		try {
			bordao.deleteBorrower(borrowerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/removeBranch/{branchId}", method = RequestMethod.GET)
	public void removeBranch(@PathVariable Integer branchId) throws SQLException {
		try {
			brdao.deleteBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/removeGenre/{genreId}", method = RequestMethod.GET)
	public void removeGenre(@PathVariable Integer genreId) throws SQLException {
		try {
			gdao.deleteGenre(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/removePublisher/{publisherId}", method = RequestMethod.GET)
	public void removePublisher(@PathVariable Integer publisherId) throws SQLException {
		try {
			pdao.deletePublisher(publisherId);
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
