/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.library.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.library.data.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByMobile(String mobile);
	Book findByMobileAndIdNot(String mobile, Long bookId);
	List<Book> findByLibrarianId(Long agentId);
	List<Book> findByIdAndLibrarianId(Long customerId, Long bookId);
        Book deletBook(Long bookId);
}