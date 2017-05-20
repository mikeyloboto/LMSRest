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
	// @RequestMapping(value = "/authors", method = RequestMethod.PUT,
	// consumes="application/json")
	@Deprecated
	public void addAuthor(@RequestBody Author author) throws SQLException {
		try {
			adao.addAuthor(author);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// @Transactional
	// @RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes
	// = "application/json")
	@Deprecated
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

	// @Transactional
	// @RequestMapping(value = "/addBorrower", method = RequestMethod.POST,
	// consumes = "application/json")
	@Deprecated
	public void addBorrower(@RequestBody Borrower g) throws SQLException {
		try {
			bordao.addBorrower(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// @Transactional
	// @RequestMapping(value = "/addBranch", method = RequestMethod.POST,
	// consumes = "application/json")
	@Deprecated
	public void addBranch(@RequestBody Branch b) throws SQLException {
		try {
			brdao.addBranch(b);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// @Transactional
	// @RequestMapping(value = "/addGenre", method = RequestMethod.POST,
	// consumes = "application/json")
	@Deprecated
	public void addGenre(@RequestBody Genre g) throws SQLException {
		try {
			gdao.addGenre(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// @Transactional
	// @RequestMapping(value = "/addLoan", method = RequestMethod.POST, consumes
	// = "application/json")
	@Deprecated
	public void addLoan(@RequestBody Loan g) throws SQLException {
		try {
			ldao.addLoanBase(g);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// @Transactional
	// @RequestMapping(value = "/addPublisher", method = RequestMethod.POST,
	// consumes = "application/json")
	@Deprecated
	public void addPublisher(@RequestBody Publisher p) throws SQLException {
		try {
			pdao.addPublisher(p);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/loans/expand", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Loan expandLoan(@RequestBody Loan loan) throws SQLException {
		try {
			return ldao.expandLoan(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loans", method = RequestMethod.DELETE, consumes = "application/json")
	public void closeLoan(@RequestBody Loan loan) throws SQLException {
		try {
			ldao.closeLoan(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/authors/all", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthors() throws SQLException {
		try {
			return adao.readAllAuthors(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthorsPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return adao.readAllAuthors(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/all", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooks() throws SQLException {
		try {
			return processAllBooks(bdao.readAllBooks(null));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooksPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return processAllBooks(bdao.readAllBooks(pageNo));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/all", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowers() throws SQLException {
		try {
			return bordao.readAllBorrowers(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getAllBorrowersPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return bordao.readAllBorrowers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/branches/all", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranches() throws SQLException {
		try {
			return brdao.readAllBranches(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/branches/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranchesPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return brdao.readAllBranches(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genres/all", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getAllGenres() throws SQLException {
		try {
			return gdao.readAllGenres(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/genres/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getAllGenresPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return gdao.readAllGenres(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loans/all", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getAllLoans() throws SQLException {
		try {
			List<Loan> ret = ldao.readAllLoans(null);
			for (Loan l : ret) {
				processBook(l.getBook());
			}
			return ret;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/loans/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Loan> getAllLoansPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			List<Loan> ret = ldao.readAllLoans(pageNo);
			for (Loan l : ret) {
				processBook(l.getBook());
			}
			return ret;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/publishers/all", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getAllPublishers() throws SQLException {
		try {
			return pdao.readAllPublishers(null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/publishers/all/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getAllPublishersPaged(@PathVariable Integer pageNo) throws SQLException {
		try {
			return pdao.readAllPublishers(pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getAuthorCount() throws SQLException {
		try {
			return adao.readAuthorCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/{id}", method = RequestMethod.GET, produces = "application/json")
	public Author getAuthorFromID(@PathVariable Integer id) throws SQLException {
		try {
			return adao.readAuthorByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsFromName(@PathVariable String searchString) throws SQLException {
		try {
			return adao.readAuthorsByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getAuthorsFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return adao.readAuthorsCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return adao.readAuthorsByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBookCount() throws SQLException {
		try {
			return bdao.readBookCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = "application/json")
	public Book getBookFromID(@PathVariable Integer id) throws SQLException {
		try {
			return processBook(bdao.readBookFromId(id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksFromName(@PathVariable String searchString) throws SQLException {
		try {
			return processAllBooks(bdao.readBookFromName("%" + searchString + "%", null));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBooksFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return bdao.readBookCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/books/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return processAllBooks(bdao.readBookFromName("%" + searchString + "%", pageNo));

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBorrowerCount() throws SQLException {
		try {
			return bordao.getBorrowerCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/{id}", method = RequestMethod.GET, produces = "application/json")
	public Borrower getBorrowerFromID(@PathVariable Integer id) throws SQLException {
		try {
			return bordao.readBorrowerByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return bordao.readBorrowersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/borrowers/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersFromName(@PathVariable String searchString)
			throws SQLException {
		try {
			return bordao.readBorrowersByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/borrowers/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBorrowersFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return bordao.readBorrowersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/branches/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBranchCount() throws SQLException {
		try {
			return brdao.readBranchCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/branches/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return brdao.readBranchesByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/branches/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesFromName(@PathVariable String searchString)
			throws SQLException {
		try {
			return brdao.readBranchesByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/branches/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getBranchesFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return brdao.readBranchesCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/branches/{id}", method = RequestMethod.GET, produces = "application/json")
	public Branch getBranchFromID(@PathVariable Integer id) throws SQLException {
		try {
			return brdao.readBranchByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genres/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getGenreCount() throws SQLException {
		try {
			return gdao.readGenreCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genres/{id}", method = RequestMethod.GET, produces = "application/json")
	public Genre getGenreFromID(@PathVariable Integer id) throws SQLException {
		try {
			return gdao.readGenreByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genres/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenresFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return gdao.readGenresByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/genres/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenresFromName(@PathVariable String searchString)
			throws SQLException {
		try {
			return gdao.readGenresByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/genres/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getGenresFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return gdao.readGenresCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/loans/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getLoanCount() throws SQLException {
		try {
			return ldao.getLoanCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/publishers/all/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getPublisherCount() throws SQLException {
		try {
			return pdao.getPublisherCount();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/publishers/{id}", method = RequestMethod.GET, produces = "application/json")
	public Publisher getPublisherFromID(@PathVariable Integer id) throws SQLException {
		try {
			return pdao.readPublisherByID(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/publishers/search/{searchString}/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishersFromNamePaged(@PathVariable String searchString, @PathVariable Integer pageNo)
			throws SQLException {
		try {
			return pdao.readPublishersByName("%" + searchString + "%", pageNo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/publishers/search/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishersFromName(@PathVariable String searchString)
			throws SQLException {
		try {
			return pdao.readPublishersByName("%" + searchString + "%", null);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/publishers/search/{searchString}/count", method = RequestMethod.GET, produces = "application/json")
	public Integer getPublishersFromNameCount(@PathVariable String searchString) throws SQLException {
		try {
			return pdao.readPublishersCountByName("%" + searchString + "%");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/authors/init", method = RequestMethod.GET, produces = "application/json")
	public Author initAuthor() throws SQLException {
		return new Author();
	}

	@RequestMapping(value = "/books/init", method = RequestMethod.GET, produces = "application/json")
	public Book initBook() throws SQLException {
		return new Book();
	}

	@RequestMapping(value = "/borrowers/init", method = RequestMethod.GET, produces = "application/json")
	public Borrower initBorrower() throws SQLException {
		return new Borrower();
	}

	@RequestMapping(value = "/branches/init", method = RequestMethod.GET, produces = "application/json")
	public Branch initBranch() throws SQLException {
		return new Branch();
	}

	@RequestMapping(value = "/genres/init", method = RequestMethod.GET, produces = "application/json")
	public Genre initGenre() throws SQLException {
		return new Genre();
	}

	@RequestMapping(value = "/loans/init", method = RequestMethod.GET, produces = "application/json")
	public Loan initLoan() throws SQLException {
		return new Loan();
	}

	@RequestMapping(value = "/publishers/init", method = RequestMethod.GET, produces = "application/json")
	public Publisher initPublisher() throws SQLException {
		return new Publisher();
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

	@Transactional
	@RequestMapping(value = "/authors", method = RequestMethod.PUT, consumes = "application/json")
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
	@RequestMapping(value = "/books", method = RequestMethod.PUT, consumes = "application/json")
	public List<Author> putBook(@RequestBody Book book) throws SQLException {
		try {
			Book temp = bdao.readBookFromId(book.getBookId());
			if (temp != null) {
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
			} else {

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

			}
			return getAllAuthors();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(value = "/borrowers", method = RequestMethod.PUT, consumes = "application/json")
	public void putBorrower(@RequestBody Borrower borrower) throws SQLException {
		try {
			Borrower temp = bordao.readBorrowerByID(borrower.getCardNo());
			if (temp != null)
				bordao.updateBorrower(borrower);
			else
				bordao.addBorrower(borrower);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/branches", method = RequestMethod.PUT, consumes = "application/json")
	public void putBranch(@RequestBody Branch branch) throws SQLException {
		try {
			Branch temp = brdao.readBranchByID(branch.getBranchNo());
			if (temp != null)
				brdao.updateBranch(branch);
			else
				brdao.addBranch(branch);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/genres", method = RequestMethod.PUT, consumes = "application/json")
	public void putGenre(@RequestBody Genre genre) throws SQLException {
		try {
			Genre temp = gdao.readGenreByID(genre.getGenreId());
			if (temp != null)
				gdao.updateGenre(genre);
			else
				gdao.addGenre(genre);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/loans", method = RequestMethod.PUT, consumes = "application/json")
	public void putLoan(@RequestBody Loan loan) throws SQLException {
		try {
			Loan temp = ldao.expandLoan(loan);
			if (temp != null)
				ldao.updateLoan(loan);
			else
				ldao.addLoanBase(loan);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/publishers", method = RequestMethod.PUT, consumes = "application/json")
	public void putPublisher(@RequestBody Publisher publisher) throws SQLException {
		try {
			Publisher temp = pdao.readPublisherByID(publisher.getPublisherId());
			if (temp != null)
				pdao.updatePublisher(publisher);
			else
				pdao.addPublisher(publisher);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/authors/{authorId}", method = RequestMethod.DELETE)
	public void removeAuthor(@PathVariable Integer authorId) throws SQLException {
		try {
			adao.deleteAuthor(authorId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
	public void removeBook(@PathVariable Integer bookId) throws SQLException {
		try {
			bdao.deleteBook(bookId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/borrowers/{borrowerId}", method = RequestMethod.DELETE)
	public void removeBorrower(@PathVariable Integer borrowerId) throws SQLException {
		try {
			bordao.deleteBorrower(borrowerId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/branches/{branchId}", method = RequestMethod.DELETE)
	public void removeBranch(@PathVariable Integer branchId) throws SQLException {
		try {
			brdao.deleteBranch(branchId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/genres/{genreId}", method = RequestMethod.DELETE)
	public void removeGenre(@PathVariable Integer genreId) throws SQLException {
		try {
			gdao.deleteGenre(genreId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	@RequestMapping(value = "/publishers/{publisherId}", method = RequestMethod.DELETE)
	public void removePublisher(@PathVariable Integer publisherId) throws SQLException {
		try {
			pdao.deletePublisher(publisherId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
