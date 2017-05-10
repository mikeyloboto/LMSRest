package com.gcit.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.library.entity.Author;

public class AuthorDAO extends BaseDAO implements ResultSetExtractor<List<Author>> {

	public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_author (authorName) values (?)", new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("update tbl_author set authorName = ? where authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_author where authorId = ?", new Object[] { author.getAuthorId() });
	}

	public void deleteAuthor(Integer authorId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_author where authorId = ?", new Object[] { authorId });
	}

	public List<Author> readAllAuthors(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_author"), this);
	}

	public Author readAuthorByID(Integer authorID) throws ClassNotFoundException, SQLException {
		List<Author> authors = template.query("select * from tbl_author where authorId = ?", new Object[] { authorID },
				this);
		if (authors != null && !authors.isEmpty()) {
			return authors.get(0);
		}
		return null;
	}

	public List<Author> readAuthorsByName(String authorName, Integer pageNo)
			throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_author where authorName like ?"), new Object[] { authorName }, this);
	}

	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while (rs.next()) {
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		return authors;
	}

	public Integer readAuthorsCountByName(String authorName) throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) from tbl_author where authorName like ?",
				new Object[] { authorName }, Integer.class);
	}

	public Integer readAuthorCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_author", Integer.class);
	}

	public List<Author> readAllAuthorsForBook(Integer bookId) {
		return template.query("select * from tbl_author where authorId in (select authorId from tbl_book_authors where bookId = ?)", new Object[]{bookId}, this);
	}
}
