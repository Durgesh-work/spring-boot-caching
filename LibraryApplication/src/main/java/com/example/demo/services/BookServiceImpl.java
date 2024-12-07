package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Book;
import com.example.demo.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	@CacheEvict(cacheNames = "allBooks", allEntries = true)
	public Book addBook(Book book) {
		
		return bookRepository.save(book);
	}

	@Override
	@CachePut(cacheNames = "books", key = "#book.id")
	@CacheEvict(cacheNames = "allBooks", allEntries = true)
	public Book updateBook(Book book) {
		bookRepository.updateAddress(book.getId(), book.getName());
		return book;
	}

	@Override
	@Cacheable(cacheNames = "books", key = "#id")
	public Book getBook(long id) {
		// TODO Auto-generated method stub
		Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return new Book();
        }
	}

	@Override
	@Caching(evict = { @CacheEvict(cacheNames = "books", key = "#id"), @CacheEvict(cacheNames = "allBooks", allEntries = true) })
	public String deleteBook(long id) {
		bookRepository.deleteById(id);
        return "Book deleted";
	}

	@Override
	@Cacheable(cacheNames = "allBooks")
	public List<Book> getAllBooks() {
		
		return bookRepository.findAll();
	}

}
