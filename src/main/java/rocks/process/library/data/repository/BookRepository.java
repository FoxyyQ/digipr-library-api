package rocks.process.library.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.library.data.domain.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByISBN(String ISBN);
        Book findByid(Long bookId);
	Book findByTitleAndIdNot(String title, Long bookId);
	List<Book> findBylibrarianId(Long id);
        List<Book> findByIdAndLibrarianId(Long bookId, Long librarianId);
        List<Book> findByAuthor(String author);
        List<Book> findByCategory(String category);
   
}