package be.archilios.library.models;

import java.util.Objects;

import static be.archilios.library.utils.Validation.*;

public class Book extends Publication {
    public static final int STANDARD_AVAILABLE_COPIES = 5;
    
    private String author;
    private String isbn;
    
    public Book(String title, String author, String isbn, int publicationYear) {
        this(
                title,
                author,
                isbn,
                publicationYear,
                STANDARD_AVAILABLE_COPIES
        );
    }
    
    public Book(String title, String author, String isbn, int publicationYear, int availableCopies) {
        super(title, publicationYear, availableCopies);
        this.setAuthor(author);
        this.setIsbn(isbn);
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setAuthor(String author) {
        validateNonEmptyString(author, "Author is required.");
        this.author = author;
    }
    
    public void setIsbn(String isbn) {
        isbn = isbn.replaceAll("-", "");
        validateIsbn(isbn, "Correct ISBN is required.");
        this.isbn = isbn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        
        Book book = (Book) o;
        return Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getIsbn(), book.getIsbn());
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getAuthor());
        result = 31 * result + Objects.hashCode(getIsbn());
        return result;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publication='" + super.toString() + '\'' +
                '}';
    }
}
