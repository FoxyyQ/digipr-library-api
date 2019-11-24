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
			// TODO create customer
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

	//TODO path = "/customer"
    
  /*  @GetMapping(path = "/book", produces = "application/json") // we need to tell the framework, to produce json code
    public List<Book> getBooks() {
		//TODO get all customers
        return bookService.findAllBooks();
    } 

	//TODO uncomment
    @GetMapping(path = "/book/{bookId}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable(value = "bookid") String bookId) {
        Book book = null;
        try {
            book= bookService.findBookByID(Integer.parseInt(bookId));
            
			//TODO find customer by id
    
        } catch (Exception e) {
			//TODO HTTP Status: not found
        }
        return ResponseEntity.ok(book);
    } */

	//TODO uncomment
    @PutMapping(path = "/customer/{customerId}", consumes = "application/json", produces = "application/json") //we have a unique id, therefor we use {customerId}
    public ResponseEntity<Book> putBook(@RequestBody Book book, @PathVariable(value = "bookId") String bookID) {
        try {
            book.setId(Long.parseLong(bookID));
          
			//TODO edit customer
            book = bookService.editBook(book);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(book);
    }

	//TODO delete mapping
    @DeleteMapping(path = "/customer/{customerId}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "bookId") String bookID) {
        try {
	//TODO delete customer
            bookService.deleteBook(Long.parseLong(bookID)); 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}