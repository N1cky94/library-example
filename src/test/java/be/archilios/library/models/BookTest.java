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
    void givenBookDetailsWithEmptyValues_whenMagazineIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new Book(" ", " ", " ", 2009)
        );
    }
}
