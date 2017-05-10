package com.gcit.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.library.entity.Book;
import com.gcit.library.entity.Branch;
import com.mysql.jdbc.Statement;

public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>> {

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book (title, pubId) values (?, ?)",
				new Object[] { book.getTitle(), book.getPublisher().getPublisherId() });
	}

	public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException {
		KeyHolder holder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into tbl_book (title, pubId) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1, book.getTitle());
				ps.setObject(2, book.getPublisher().getPublisherId());
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	public void addBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book_authors values (?, ?)", new Object[] { bookId, authorId });
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("update tbl_book set title = ? where bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book where bookId = ?", new Object[] { book.getBookId() });
	}

	public void deleteBook(Integer bookId) throws ClassNotFoundException, SQLException {
		// System.out.println("deleting");
		template.update("delete from tbl_book where bookId = ?", new Object[] { bookId });
	}

	public Integer getBookCopies(Book book, Branch branch) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?",
				new Object[] { book.getBookId(), branch.getBranchNo() }, Integer.class);
	}

	public List<Book> readBookListInBranch(Branch branch, Integer pageNo) {
		setPageNo(pageNo);

		return template.query(
				"select * from tbl_book where bookId in (select bookId from tbl_book_copies where branchId = ?  and noOfCopies > 0)",
				new Object[] { branch.getBranchNo() }, this);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setTitle(rs.getString("title"));
			b.setBookId(rs.getInt("bookId"));
			books.add(b);
		}
		return books;
	}

	public List<Book> readAllBooks(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_book"), this);

	}

	public Book readBookFromId(Integer id) throws ClassNotFoundException, SQLException {
		List<Book> books = template.query("select * from tbl_book where bookId = ?", new Object[] { id }, this);
		if (books != null && !books.isEmpty()) {
			return books.get(0);
		}
		return null;
	}

	public Integer readBookCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_book", Integer.class);
	}

	public void removeBookAuthors(Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book_authors where bookId = ?", new Object[] { bookId });
	}

	public void updateBookPublisher(Book book) throws ClassNotFoundException, SQLException {
		template.update("update tbl_book set pubId = ? where bookId = ?",
				new Object[] { book.getPublisher().getPublisherId(), book.getBookId() });
	}

	public Integer readBookCopiesCountInBranch(Branch branch) throws ClassNotFoundException, SQLException {
		return template.queryForObject(
				"select count(*) as COUNT from tbl_book_copies where branchId = ? and noOfCopies > 0",
				new Object[] { branch.getBranchNo() }, Integer.class);
	}

	public List<Book> readBookFromName(String string, Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_book where title like ?"), new Object[] { string }, this);
	}

	public Integer readBookCountByName(String string) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) from tbl_book where title like ?", new Object[] { string },
				Integer.class);
	}

}
