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

	public Book editBook(@Valid Book customer) throws Exception {
		if (customer.getId() == null) {
			if (bookRepository.findByMobile(customer.getMobile()) == null) {
				customer.setLibrarian(librarianService.getCurrentLibrarian());
				return bookRepository.save(customer);
			}
			throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
		}
		if (bookRepository.findByMobileAndIdNot(customer.getMobile(), customer.getId()) == null) {
			if (customer.getLibrarian() == null) {
				customer.setLibrarian(librarianService.getCurrentLibrarian());
			}
			return bookRepository.save(customer);
		}
		throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
	}

	public void deleteBook(Long bookId)
	{
		bookRepository.deleteById(bookId);
	}
	
	public Book findCustomerById(Long customerId) throws Exception {
		List<Book> customerList = bookRepository.findByIdAndLibrarianId(customerId, librarianService.getCurrentLibrarian().getId());
		if(customerList.isEmpty()){
			throw new Exception("No customer with ID "+customerId+" found.");
		}
		return customerList.get(0);
	}

	public List<Book> findAllCustomers() {
		return bookRepository.findByLibrarianId(librarianService.getCurrentLibrarian().getId());
	}
	
}
