package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.Book;

public interface BookService {
	
	List<Book> getAllBooks();

	Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(long id);

    String deleteBook(long id);
}
