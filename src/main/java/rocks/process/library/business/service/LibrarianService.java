/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.library.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.library.data.domain.Librarian;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.Validator;
import rocks.process.library.data.repository.LibrarianRepository;

@Service
@Validated
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    Validator validator;

    public void saveLibrarian(@Valid Librarian librarian) throws Exception {
        if (librarian.getId()== null) {
            if (librarianRepository.findByEmail(librarian.getEmail()) != null) {
                throw new Exception("Email address " + librarian.getEmail() + " already assigned another librarian.");
            }
        } else if (librarianRepository.findByEmailAndIdNot(librarian.getEmail(), librarian.getId()) != null) {
            throw new Exception("Email address " + librarian.getEmail() + " already assigned another librarian.");
        }
        librarianRepository.save(librarian);
    }

    public Librarian getCurrentLibrarian() {
        String userEmail = "demo@demo.ch";
        return librarianRepository.findByEmail(userEmail);
    }

    @PostConstruct
    private void init() throws Exception {
        Librarian librarian = new Librarian();
        librarian.setName("Demo");
        librarian.setEmail("demo@demo.ch");
        librarian.setNote("busy");
     //   librarian.setPassword("password");
        this.saveLibrarian(librarian);
    }
      
}

/*        Librarian findByLibrarainID(Long Id);
	Librarian findByEmail(String email);
	Librarian findByEmailAndIdNot(String email, Long agentId);
        Librarian findByStatus(String status);
        Librarian findByName(String name);*/