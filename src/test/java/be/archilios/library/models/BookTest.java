package be.archilios.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private final static int YEAR_IN_FUTURE = (LocalDate.now().getYear() + 1);
    
    @Test
    void givenBookDetails_whenBookIsCreated_thanBookIsCorrectlyStoredInMemory() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009);
        
        assertNotNull(book);
        assertEquals("Clean Code", book.getTitle(), "Title should be set properly");
        assertEquals("Robert C. Martin", book.getAuthor(), "Author should be set properly");
        assertEquals("9780132350884", book.getIsbn(), "ISBN should be set properly");
        assertEquals(2009, book.getPublicationYear(), "Publication year should be set properly");
        assertEquals(Book.STANDARD_AVAILABLE_COPIES, book.getAvailableCopies(), "Available copies are set automatically");
    }
    
    @Test
    void givenBookDetailsWithAvailableCopies_whenBookIsCreated_thanBookIsCorrectlyStoredInMemory() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 10);
        
        assertNotNull(book);
        assertEquals(10, book.getAvailableCopies(), "Available copies should be set");
    }
    
    @Test
    void givenIsbnWithHyphens_whenBookIsCreated_thanIsbnIsConvertedProperly() {
        String providedIsbn = "978-0-132-35088-4";
        String expectedIsbn = "9780132350884";
        
        Book book = new Book("Clean Code", "Robert C. Martin", providedIsbn, 2009);
        
        assertEquals(expectedIsbn, book.getIsbn(), "ISBN should be properly converted");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"123456789123", "12345678912345", "123-4-57-12345-1", "123-4-5667-12345-1"})
    void givenBookDetailsWithInvalidIsbn_whenBookIsCreated_thanDomainExceptionIsThrown(String invalidIsbn) {
        assertThrows(
                DomainException.class,
                () -> new Book("Clean Code", "Robert C. Martin", invalidIsbn, 2009)
        );
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2025, -1})
    void giveBookDetailsWithInvalidPublicationYear_whenBookIsCreated_thanDomainExceptionIsThrown(int invalidPublicationYear) {
        assertThrows(
                DomainException.class,
                () -> new Book("Clean Code", "Robert C. Martin", "9780132350884", invalidPublicationYear)
        );
    }
    
    @Test
    void givenBookDetailsWithNullValues_whenBookIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new Book(null, null, null, 2009)
        );
    }
    
    @Test
    void givenBookDetailsWithEmptyValues_whenBookIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new Book(" ", " ", " ", 2009)
        );
    }
    
    @Test
    void givenBookWithOneAvailableCopy_whenBookIsLendOut_thanAvailableCopiesShouldReduceToZero() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 1);
        
        book.lendPublication();
        
        assertEquals(0, book.getAvailableCopies(), "Available copies should be zero");
    }
    
    @Test
    void givenBookWithNoAvailableCopy_whenBookIsLendOut_thanDomainExceptionIsThrown() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 0);
        
        assertThrows(
                DomainException.class,
                () -> book.lendPublication()
        );
        
        assertEquals(0, book.getAvailableCopies(), "Available copies should not have changed");
    }
    
    @Test
    void whenBookIsReturned_thanAvailableCopiesShouldIncrementByOne() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 0);
        
        book.returnPublication();
        
        assertEquals(1, book.getAvailableCopies(), "Available copies should increment to one");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"9780132350884", "978-0-132-35088-4"})
    void equals_returnsTrueForEqualBooks(String isbn) {
        Book book1 = new Book("Clean Code", "Robert C. Martin", isbn, 2009);
        Book book2 = new Book("Clean Code", "Robert C. Martin", isbn, 2009);
        assertEquals(book1, book2);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"9780132350884", "978-0-132-35088-4"})
    void equals_returnsFalseForDifferentBooks(String isbn) {
        Book book1 = new Book("Clean Code", "Robert C. Martin", isbn, 2009);
        Book book2 = new Book("Clean Code", "Another Author", isbn, 2009);
        assertNotEquals(book1, book2);
    }
    
    @Test
    void hashCode_returnsSameHashCodeForEqualBooks() {
        Book book1 = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009);
        assertEquals(book1.hashCode(), book2.hashCode());
    }
    
    @Test
    void hashCode_returnsDifferentHashCodeForDifferentBooks() {
        Book book1 = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009);
        Book book2 = new Book("Clean Code", "Another Author", "9780132350884", 2009);
        assertNotEquals(book1.hashCode(), book2.hashCode());
    }
    
    @Test
    void toString_returnsCorrectStringRepresentation() {
        Book book = new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009);
        String expected = "Book{author='Robert C. Martin', isbn='9780132350884', publication='Publication{title='Clean Code', publicationYear=2009, availableCopies=5}'}";
        assertEquals(expected, book.toString());
    }
}
