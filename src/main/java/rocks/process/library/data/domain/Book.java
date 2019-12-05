package rocks.process.library.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;


@Entity
public class Book {

	@Id
	@GeneratedValue
	private Long id;
        
        @ISBN(message="This is not a valid ISBN number, please provide a 13-digit number!")
        private String ISBN;
	@NotEmpty(message = "Please provide a title.")
        @Size(max=20, message="Title must be less than 25 characters")
        private String title;
	private String author;
	private String category;
        private String publisher;
        
	@ManyToOne
	@JsonBackReference
	private Librarian librarian;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getISBN() {
        return ISBN;
    }


    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

  
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Librarian getLibrarian() {
        return librarian;
    }


    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    public String getPublisher() {
        return publisher;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
      
}
