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

	public void deleteBook(Long bookId)
	{
            bookRepository.deleteById(bookId);
      		
	} 
	
	public Book findByid(Long bookId) throws Exception {
            
   List<Book> bookList= bookRepository.findByIdAndLibrarianId(bookId, librarianService.getCurrentLibrarian().getId());
            		
		if(bookList.isEmpty()){
			throw new Exception("No book with ID "+bookId+" found.");
		}
		return bookList.get(0);
	}

	public List<Book> findAllBooks() {
		return bookRepository.findBylibrarianId(librarianService.getCurrentLibrarian().getId());
	}
	
        
       public Book findByISBN(String ISBN)throws Exception{
    
           if(bookRepository.findByISBN(ISBN)==null){
          throw new Exception("No book with ISBN "+ISBN+" found.");
               
           }	
                return bookRepository.findByISBN(ISBN);        
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
   
}
