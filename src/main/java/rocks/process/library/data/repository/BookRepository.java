/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.

UC1: Employee login to library application 

UC2: User can search for an existing book in library.

UC3: User can modify entries. 

UC4: User can use a filter function.

UC5: Employee is able to set and update his status to inform other users about the current status.

UC6: User can check status of other User. 


 */

package rocks.process.library.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.library.data.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByISBN(Long ISBN);
        Book findByid(Long id);
	Book findByTitleAndIdNot(String title, Long id);
	List<Book> findBylibrarianId(Long librarianId);
	//List<Book> findByidAndlibrarianId(Long id, Long id);
        List<Book> findByAuthor(String author);
        List<Book> findByCategory(String category);
     //   Book deleteBook(Long id);
}