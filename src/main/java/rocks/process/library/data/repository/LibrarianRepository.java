/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.library.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.library.data.domain.Librarian;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
        
	Librarian findByEmail(String email);
	Librarian findByEmailAndIdNot(String email, Long id);
        Librarian findByStatus(String status);
        Librarian findByName(String name);
}
