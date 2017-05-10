package com.gcit.library.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcit.library.entity.Author;
import com.gcit.library.entity.Book;
import com.gcit.library.entity.Genre;
import com.gcit.library.entity.Publisher;
import com.gcit.library.service.AdminService;

/**
 * Test Controller, will probably have to rewrite alot of that shite
 */
// @WebServlet({ "/addBook", "/editBook", "/removeBook", "/searchBooks" })
@Controller
public class AdminBookController {

	@Autowired
	AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/searchBooks", method = RequestMethod.GET)
	public @ResponseBody String searchBook(@RequestParam("searchString") String searchString,
			@RequestParam("pageNo") Integer pageNo, Locale locale, Model model) {
		String table = searchBooks(searchString, pageNo);
		String pagination = pageBooks(searchString, pageNo);
		return table + '\n' + pagination;
	}

	private String pageBooks(String searchString, Integer pageNo) {
		StringBuffer strBuf = new StringBuffer();
		try {
			Integer count = adminService.getBooksFromNameCount(searchString);
			Integer pages = 1;
			if (count != 0) {
				if (count % 10 == 0) {
					pages = count / 10;
				} else {
					pages = count / 10 + 1;
				}
			}
			for (int i = 1; i <= pages; i++) {
				strBuf.append("<li><a href=\"#\" onclick=\"searchBook(" + i + ")\">" + i + "</a></li>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	private String searchBooks(String searchString, Integer pageNo) {
		if (pageNo == null)
			pageNo = 1;
		StringBuffer strBuf = new StringBuffer();
		try {
			List<Book> books = adminService.getBooksFromName(pageNo, searchString);
			// System.out.println(books.size());
			for (Book a : books) {
				strBuf.append("<tr><td>" + (books.indexOf(a) + 1 + (pageNo - 1) * 10) + "</td><td>" + a.getDescription()
						+ "</td>");
				strBuf.append("<td>" + a.getGenreList() + "</td><td>" + a.getPublisher().getPublisherName() + "</td>");

				strBuf.append("<td><button type=\"button\" class=\"btn btn-primary\""
						+ " data-toggle=\"modal\" data-target=\"#editBookModal\"" + " href=\"adminBookEdit?bookId="
						+ a.getBookId() + "\">Update</button> ");
				strBuf.append("<a type=\"button\" class=\"btn btn-danger\"" + " href=\"removeBook?bookId="
						+ a.getBookId() + "\">Delete</a></td></tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	@RequestMapping(value = "/adminBookAdd", method = RequestMethod.GET)
	public String bookAddModal(Locale locale, Model model) {
		try {
			StringBuffer authorsBuffer = new StringBuffer();
			for (Author a : adminService.getAllAuthors(null)) {
				authorsBuffer.append("<option value=\"" + a.getAuthorId() + "\">" + a.getAuthorName() + "</option>");
			}

			StringBuffer genresBuffer = new StringBuffer();
			for (Genre g : adminService.getAllGenres(null)) {
				System.out.println(g.getGenreName());
				genresBuffer.append("<option value=\"" + g.getGenreId() + "\">" + g.getGenreName() + "</option>");
			}

			StringBuffer publishersBuffer = new StringBuffer();
			for (Publisher p : adminService.getAllPublishers(null)) {
				publishersBuffer
						.append("<option value=\"" + p.getPublisherId() + "\">" + p.getPublisherName() + "</option>");
			}

			model.addAttribute("authors", authorsBuffer.toString());
			model.addAttribute("genres", genresBuffer.toString());
			model.addAttribute("publishers", publishersBuffer.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "adminBookAdd";
	}

	@RequestMapping(value = "/adminBookEdit", method = RequestMethod.GET)
	public String bookEditModal(@RequestParam("bookId") Integer bookId, Locale locale, Model model) {
		try {
			StringBuffer authorsBuffer = new StringBuffer();
			for (Author a : adminService.getAllAuthors(null)) {
				authorsBuffer.append("<option value=\"" + a.getAuthorId() + "\">" + a.getAuthorName() + "</option>");
			}

			StringBuffer genresBuffer = new StringBuffer();
			for (Genre g : adminService.getAllGenres(null)) {
				System.out.println(g.getGenreName());
				genresBuffer.append("<option value=\"" + g.getGenreId() + "\">" + g.getGenreName() + "</option>");
			}

			StringBuffer publishersBuffer = new StringBuffer();
			for (Publisher p : adminService.getAllPublishers(null)) {
				publishersBuffer
						.append("<option value=\"" + p.getPublisherId() + "\">" + p.getPublisherName() + "</option>");
			}

			model.addAttribute("book", adminService.getBookFromID(bookId));
			model.addAttribute("authors", authorsBuffer.toString());
			model.addAttribute("genres", genresBuffer.toString());
			model.addAttribute("publishers", publishersBuffer.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "adminBookEdit";
	}

	@RequestMapping(value = "/removeBook", method = RequestMethod.GET)
	private String removeBook(@RequestParam("bookId") Integer bookId, Locale locale, Model model) {
		// TODO Auto-generated method stub
		try {
			adminService.removeBook(bookId);
			model.addAttribute("message",
					"<div class=\"alert alert-success\" role=\"alert\"><strong>Success!</strong> Book successfully deleted. </div>");
		} catch (NumberFormatException e) {
			model.addAttribute("message",
					"<div class=\"alert alert-danger\" role=\"alert\"> <strong>Oops!</strong> Something went wrong. </div>");
			e.printStackTrace();
		} catch (SQLException e) {
			model.addAttribute("message",
					"<div class=\"alert alert-danger\" role=\"alert\"> <strong>Oops!</strong> Something went wrong. </div>");
			e.printStackTrace();
		}
		return "adminBookManage";
	}

	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	private String editBook(@RequestParam("bookId") Integer bookId, @RequestParam("bookName") String bookName,
			@RequestParam("authors") String authorIds[], @RequestParam("genres") String[] genreIds,
			@RequestParam("publisher") Integer publisher, Locale locale, Model model) {
		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(bookName);
		List<Author> authors = new ArrayList<>();
		if (authorIds != null && authorIds.length > 0) {
			for (int i = 0; i < authorIds.length; i++) {
				Author a = new Author();
				a.setAuthorId(Integer.parseInt(authorIds[i]));
				System.out.println("adding authorId " + authorIds[i]);
				authors.add(a);
			}
		}

		List<Genre> genres = new ArrayList<>();
		if (genreIds != null && genreIds.length > 0) {

			for (int i = 0; i < genreIds.length; i++) {
				Genre g = new Genre();
				g.setGenreId(Integer.parseInt(genreIds[i]));
				genres.add(g);
			}
		}
		Publisher pub = new Publisher();
		pub.setPublisherId(publisher);

		book.setAuthors(authors);
		book.setGenres(genres);
		book.setPublisher(pub);

		try {
			adminService.modBook(book);
			model.addAttribute("message",
					"<div class=\"alert alert-success\" role=\"alert\"> <strong>Success!</strong> Book details successfully updated. </div>");
		} catch (SQLException e) {
			model.addAttribute("message",
					"<div class=\"alert alert-danger\" role=\"alert\"> <strong>Oops!</strong> Something went wrong. </div>");
			e.printStackTrace();
		}
		return "adminBookManage";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	private String addBook(@RequestParam("bookName") String bookName, @RequestParam("authors") String[] authorIds,
			@RequestParam("genres") String[] genreIds, @RequestParam("publisher") Integer publisher, Locale locale,
			Model model) {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setTitle(bookName);
		List<Author> authors = new ArrayList<>();
		if (authorIds != null && authorIds.length > 0) {
			for (int i = 0; i < authorIds.length; i++) {
				Author a = new Author();
				a.setAuthorId(Integer.parseInt(authorIds[i]));
				System.out.println("adding authorId " + authorIds[i]);
				authors.add(a);
			}
		}

		List<Genre> genres = new ArrayList<>();
		if (genreIds != null && genreIds.length > 0) {

			for (int i = 0; i < genreIds.length; i++) {
				Genre g = new Genre();
				g.setGenreId(Integer.parseInt(genreIds[i]));
				genres.add(g);
			}
		}
		Publisher pub = new Publisher();
		pub.setPublisherId(publisher);

		book.setAuthors(authors);
		book.setGenres(genres);
		book.setPublisher(pub);

		try {
			adminService.addBook(book);
			model.addAttribute("message",
					"<div class=\"alert alert-success\" role=\"alert\"> <strong>Success!</strong> Book successfully added. </div>");
		} catch (SQLException e) {
			model.addAttribute("message",
					"<div class=\"alert alert-danger\" role=\"alert\"> <strong>Oops!</strong> Something went wrong. </div>");
			e.printStackTrace();
		}
		return "adminBookManage";
	}

}
