package com.gcit.library.utility;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.gcit.library.entity.Book;

public class BookSerializer extends StdSerializer<Book> {

	private static final long serialVersionUID = 915837654548825358L;

	protected BookSerializer() {
		super(Book.class);
	}

	 public void serialize(Book value, JsonGenerator gen, SerializerProvider sp) throws IOException, JsonProcessingException {
	        gen.writeString(value.getBookId().toString());
	    }
	
}
