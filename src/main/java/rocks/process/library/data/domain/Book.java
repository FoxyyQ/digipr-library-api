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
	
        
        @ISBN(message="ISBN number must be equal to 13 digits")
        private Long ISBN;
	@NotEmpty(message = "Please provide a title.")
        @Size(max=20, message="Title must be less than 25 characters")
        private String title;
	private String author;
	private String category;
        
	@ManyToOne
	@JsonBackReference
	private Librarian librarian;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ISBN
     */
    public Long getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the librarian
     */
    public Librarian getLibrarian() {
        return librarian;
    }

    /**
     * @param librarian the librarian to set
     */
    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

        
}
