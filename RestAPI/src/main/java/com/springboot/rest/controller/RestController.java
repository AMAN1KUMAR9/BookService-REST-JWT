package com.springboot.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.rest.dao.Dao;
import com.springboot.rest.entity.Book;
import com.springboot.rest.services.BookServices;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;




@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	 private BookServices restServices;
	
	@Autowired
	private Dao dao;
	
	@GetMapping("/books") // get all books
	public ResponseEntity<List<Book> >getbooks( ) {
		List<Book> list = restServices.getAllBooks();
		if (list.size()<= 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
		 return  ResponseEntity.of(Optional.of(list))  ;
	}
	
	@GetMapping("/books/paginate") // get paginated books
	public ResponseEntity<Page<Book>> getPaginatedBooks(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "id") String sortBy) {
	    
	    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
	    Page<Book> books = dao.findAll(pageable);
	    
	    return ResponseEntity.ok(books);
	}

	
	
	@GetMapping("/books/{id}") // get book by ID
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = restServices.getBook(id);
		
		if (book == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/books") // add a new book
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		
		Book b = null;
		try {
			
			b = this.restServices.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@DeleteMapping("/books/{id}")  // delete a book by ID
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		try {
			
			restServices.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		catch (Exception e) {
			return  ResponseEntity.internalServerError().build();
		}
	}
	
	
	@PutMapping("/books/{id}")  // update a book by ID
	public ResponseEntity<Book> putMethodName(@PathVariable("id") int id, @RequestBody Book book) {
	
		try {
			 restServices.update(book, id);
			
			return ResponseEntity.ok().body(book) ; 
		}catch (Exception e) {
			return  ResponseEntity.internalServerError().build();
		}
		
	}
	
	
    @GetMapping("/books/search") // search books by title
    public  ResponseEntity<List<Book> > searchBooks(@RequestParam String title) {
    	
    	 List<Book> list =  restServices.searchBooks(title);
    	if (list.size()<= 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
		 return  ResponseEntity.of(Optional.of(list))  ;
    	
        
    }

    
    @GetMapping("/books/filter/author") // filter books by author
    public  ResponseEntity<List<Book> >filterByAuthor(@RequestParam String author) {
    	 List<Book> list =  restServices.filterByAuthor(author);
    	 
        if (list.size()<= 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
		 return  ResponseEntity.of(Optional.of(list))  ;
   	
    }

  
    @GetMapping("/books/filter/category") // filter books by category
    public  ResponseEntity<List<Book> >filterByCategory(@RequestParam String category) {
    	 List<Book> list =  restServices.filterByCategory(category);
        if (list.size()<= 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
		 return  ResponseEntity.of(Optional.of(list))  ;
   	
    }

  
    @GetMapping("/books/filter/rating") // filter books by rating
    public  ResponseEntity<List<Book> > filterByRating(@RequestParam int rating) {
    	 List<Book> list =  restServices.filterByRating(rating);
        if (list.size()<= 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
		
		 return  ResponseEntity.of(Optional.of(list))  ;
   	
    }
    

  
    @GetMapping("/books/filter") // filter books by multiple criteria
    public ResponseEntity<List<Book>> filterBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer rating) {
        
        List<Book> list = restServices.filterBooks(author, category, rating);

        if (list.size()<=0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }

        return ResponseEntity.of(Optional.of(list)); 
    }


}
