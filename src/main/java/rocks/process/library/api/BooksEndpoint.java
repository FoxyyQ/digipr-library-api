/*
 * Copyright (c) 2019. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.library.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rocks.process.library.business.service.BookService;
import rocks.process.library.data.domain.Book;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BooksEndpoint {
    @Autowired
    private BookService bookService;

	//TODO uncomment
    @PostMapping(path = "/book", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        try {
			// TODO create book
            book = bookService.editBook(book);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{bookId}")
                .buildAndExpand(Long.toString(book.getId())).toUri();
         
        return ResponseEntity.created(location).body(book);
    }

	//TODO path = "/book"
    
    @GetMapping(path = "/book", produces = "application/json") // we need to tell the framework, to produce json code
    public List<Book> getBooks() {
		//TODO get all customers
        return bookService.findAllBooks();
    } 

	//TODO uncomment
    @GetMapping(path = "/book/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable(value = "bookId") String bookId) {
        Book book = null;
        try {
            book= bookService.findByid(Long.parseLong(bookId)) ; // find book by id
    
        } catch (Exception e) {
	 throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok(book);
    } 
    
        @GetMapping(path = "/book/{ISBN}", produces = "application/json")
    public ResponseEntity<Book> getBookISBN(@PathVariable(value = "ISBN") String ISBN) {
        Book book = null;
        try {
            book=bookService.findByISBN(ISBN); // find by ISBN
   
        } catch (Exception e) {
		  throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping(path = "/book/{bookId}", consumes = "application/json", produces = "application/json") //we have a unique id, therefor we use {customerId}
    public ResponseEntity<Book> putBook(@RequestBody Book book, @PathVariable(value = "bookId") String id) {
        try {
            book.setId(Long.parseLong(id));

            book = bookService.editBook(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(book);
    }

    @DeleteMapping(path = "/book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "bookId") String bookId) {
        try {
            bookService.deleteBook(Long.parseLong(bookId)); 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}