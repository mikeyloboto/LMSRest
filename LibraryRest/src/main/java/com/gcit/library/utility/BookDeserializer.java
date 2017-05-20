package com.gcit.library.utility;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gcit.library.entity.Book;

public class BookDeserializer extends StdDeserializer<Book> {

	private static final long serialVersionUID = -3173524087961329631L;

	protected BookDeserializer() {
		super(Book.class);
	}

	@Override
	public Book deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Book book = new Book();
		JsonNode bookNode = jp.readValueAsTree();
		book.setBookId(bookNode.get("bookId").asInt());
		return book;
	}
}
