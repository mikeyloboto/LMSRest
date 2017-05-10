package com.gcit.library.entity;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable{
	
	private static final long serialVersionUID = -1070838265691816263L;

	private Integer bookId;
	private String title;
	private List<Author> authors;
	private Publisher publisher;
	public List<Genre> genres;
	/**
	 * @return the bookId
	 */
	public Integer getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the authors
	 */
	public List<Author> getAuthors() {
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	/**
	 * @return the publisher
	 */
	public Publisher getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the genres
	 */
	public List<Genre> getGenres() {
		return genres;
	}
	/**
	 * @param genres the genres to set
	 */
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	
	public String getDescription() {
		String authorList = "";
		if (this.authors != null)
			if (!this.authors.isEmpty())
				for (Author a : authors) {
					authorList += (a.getAuthorName() + ", ");
				}
		if (!authorList.equals("")) {
			authorList = authorList.substring(0, authorList.length() - 2);
		}
		return this.getTitle() + " by " + authorList;
	}
	
	public String getGenreList() {
		String genreList = "";
		if (this.genres != null)
			if (!this.genres.isEmpty())
				for (Genre g : genres) {
					genreList += (g.getGenreName() + ", ");
				}
		if (!genreList.equals("")) {
			genreList = genreList.substring(0, genreList.length() - 2);
		}
		return genreList;
	}
}
