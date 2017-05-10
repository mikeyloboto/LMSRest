package com.gcit.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.library.entity.Genre;

public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>>{

	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public Integer addGenreWithID(Genre genre) throws ClassNotFoundException, SQLException {
		return template.update("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenreName() });
	}

	public void addBookGenre(Genre g, Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("insert into tbl_book_genres values (?, ?)", new Object[] { g.getGenreId(), bookId });
	}

	public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("update tbl_genre set genre_name = ? where genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}

	public void deleteGenre(Integer genreId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_genre where genre_id = ?", new Object[] { genreId });
	}

	public Genre readGenreByID(Integer id) throws ClassNotFoundException, SQLException {
		List<Genre> genres = template.query("select * from tbl_genre where genre_id = ?", new Object[] { id }, this);
		if (genres != null && !genres.isEmpty()) {
			return genres.get(0);
		}
		return null;
	}

	public List<Genre> readAllGenres(Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_genre"), this);

	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre g = new Genre();
			g.setGenreName(rs.getString("genre_name"));
			g.setGenreId(rs.getInt("genre_id"));
			genres.add(g);
		}
		return genres;
	}

	public void removeBookGenres(Integer bookId) throws ClassNotFoundException, SQLException {
		template.update("delete from tbl_book_genres where bookId = ?", new Object[] { bookId });
	}

	public Integer readGenreCount() throws ClassNotFoundException, SQLException {
		return template.queryForObject("select count(*) as COUNT from tbl_genre", Integer.class);
	}

	public List<Genre> readGenresByName(String string, Integer pageNo) throws ClassNotFoundException, SQLException {
		setPageNo(pageNo);
		return template.query(addLimit("select * from tbl_genre where genre_name like ?"), new Object[]{string}, this);
	}

	public Integer readGenresCountByName(String string) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return template.queryForObject("select count(*) from tbl_genre where genre_name like ?", new Object[]{string}, Integer.class);
	}

	public List<Genre> readAllGenresForBook(Integer bookId) {
		return template.query("select * from tbl_genre where genre_id in (select genre_id from tbl_book_genres where bookId = ?)", new Object[]{bookId}, this);
	}

}
