/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.library.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.library.data.domain.Book;

import javax.validation.Valid;
import java.util.List;
import rocks.process.library.data.repository.BookRepository;

@Service
@Validated
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LibrarianService librarianService;

	public Book editBook(@Valid Book book) throws Exception {
		if (book.getId() == null) {
			if (bookRepository.findByISBN(book.getISBN()) == null) {
				book.setLibrarian(librarianService.getCurrentLibrarian());
				return bookRepository.save(book);
			}
			throw new Exception("Invalid ISBN number " + book.getISBN()+ " is wrong or belongs to another book.");
		}
		if (bookRepository.findByTitleAndIdNot(book.getTitle(),book.getId()) == null) {
			if (book.getLibrarian() == null) {
				book.setLibrarian(librarianService.getCurrentLibrarian());
			}
			return bookRepository.save(book);
		}
			throw new Exception("Invalid ISBN number " + book.getISBN()+ " is wrong or belongs to another book.");
	}

	public void deleteBook(Long id)
	{
		bookRepository.deleteById(id);
	}
	
	public Book findBylibrarianId(Long librarianId) throws Exception {
            
                List<Book> bookList= bookRepository.findBylibrarianId(librarianId);
		
		if(bookList.isEmpty()){
			throw new Exception("No book assigned to Librarian ID "+librarianId+".");
		}
		return bookList.get(0);
	}

	public List<Book> findAllBooks() {
		return bookRepository.findBylibrarianId(librarianService.getCurrentLibrarian().getId());
	}
	
        
       public Book findByISBN(Long ISBN)throws Exception{
           
           if(bookRepository.findByISBN(ISBN)==null){
          throw new Exception("No book with ISBN "+ISBN+" found.");
               
           }	
                return bookRepository.findByISBN(ISBN);        
       }     
       
        public Book findByid(Long id)throws Exception{
           
           if(bookRepository.findByid(id)==null){
          throw new Exception("No book with ISBN "+id+" found.");
               
           }	
                return bookRepository.findByid(id);        
       }    
        
        
        public List<Book> findByAuthor (String author) throws Exception{
            
            if(bookRepository.findByAuthor(author)==null){
                throw new Exception ("There is no author with the name " +author);
            }
            
            return bookRepository.findByAuthor(author);
        }
        
        
        
        public List<Book> findByCategory (String category) throws Exception{
            
            if(bookRepository.findByAuthor(category)==null){
                throw new Exception ("There is no author with the name " +category);
            }
            
            return bookRepository.findByAuthor(category);
        }
       
       /*List<Book> findByLibrarianId(Long librarianId);
	List<Book> findByIdAndLibrarianId(Long Id, Long librarianId);
        List<Book> findByAuthor(String author);
        List<Book> findByCategory(String category);

*/
}
