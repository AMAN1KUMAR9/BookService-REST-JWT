package com.springboot.rest.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.rest.dao.Dao;
import com.springboot.rest.entity.Book;

@Service
public class BookServices {
	@Autowired
	private Dao dao;

	// retrieves all books
	public List<Book> getAllBooks() {

	List<Book> list = (List<Book>) dao.findAll();
		return list;
		
	}
	 //Retrieves a book by ID
	public Book getBook(int id)
	{

		Book byId = dao.findById(id);
		return byId;
	}
	
	// adds a new book
	public Book addBook(Book book) {

		return dao.save(book);
	}
	
	// deletes a book by ID
	public void deleteBook(int id ) {
	

		dao.deleteById(id);
		}
	
	// Updates a book if the provided ID matches
	public Book update (Book book , int id) {

		if (book.getId() == id) {dao.save(book);}
		
		return book;
		
		
	}
	
	// Searches books by title
    public List<Book> searchBooks(String title) {
        return dao.findByTitleContainingIgnoreCase(title);
    }

    // filters books by author
    public List<Book> filterByAuthor(String author) {
        return dao.findByAuthor(author);
    }

    // filters books by category
    public List<Book> filterByCategory(String category) {
        return dao.findByCategoryIgnoreCase(category);
    }

 // filter books by rating greater than or equal to value
    public List<Book> filterByRating(int rating) {
        return dao.findByRatingGreaterThanEqual(rating);
    }

 //   filters books using multiple parameters
    public List<Book> filterBooks(String author, String category, Integer rating) {
        return dao.filterBooks(author, category, rating);
    }


	}




