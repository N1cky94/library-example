package be.archilios.library.models;

import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    
    public Book(String title, String author, String isbn, int publicationYear) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setIsbn(isbn);
        this.setPublicationYear(publicationYear);
    }
    
    public Book(Book book) {
        this(
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear()
        );
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Book book = (Book) o;
        return getPublicationYear() == book.getPublicationYear() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getIsbn(), book.getIsbn());
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getAuthor());
        result = 31 * result + Objects.hashCode(getIsbn());
        result = 31 * result + getPublicationYear();
        return result;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
