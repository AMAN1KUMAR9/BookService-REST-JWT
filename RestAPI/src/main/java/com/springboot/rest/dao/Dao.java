package com.springboot.rest.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Book;

@Repository
public interface Dao extends CrudRepository<Book, Integer> {
	// find a book by its ID
	public Book findById(int id) ;
	

    List<Book> findByTitleContainingIgnoreCase(String title);  // it search books by title using LIKE keyword in SQL

   
    List<Book> findByAuthorIgnoreCase(String author); // it filter books by author

   
    List<Book> findByCategoryIgnoreCase(String category);  // it filters books by category

    
    List<Book> findByRatingGreaterThanEqual(int rating);// it filter books by rating greater than or equal to the given rating

    @Query("SELECT b FROM Book b WHERE " +
    	       "(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))")
    	List<Book> findByAuthor(String author);  // it filters books by letter or word containing author 
    
    
    @Query("SELECT b FROM Book b WHERE " +
    		"(:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))) AND"+
           "(:category IS NULL OR LOWER(b.category) = LOWER(:category)) AND " +
           "(:rating IS NULL OR b.rating >= :rating)")
    List<Book> filterBooks(String author, String category, Integer rating);  // custom Query to filter by multiple parameters


	public Page<Book> findAll(Pageable pageable);
}
		

