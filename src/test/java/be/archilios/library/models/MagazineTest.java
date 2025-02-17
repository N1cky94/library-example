package be.archilios.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MagazineTest {
    
    @Test
    void giveMagazineDetails_whenMagazineIsCreated_thanMagazineIsCorrectlyStoredInMemory() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012);
        
        assertNotNull(magazine);
        assertEquals("How to start using Spring Boot", magazine.getTitle(), "Title should be set properly");
        assertEquals("Baeldung", magazine.getEditor(), "Editor should be set properly");
        assertEquals("12345678", magazine.getIssn(), "ISSN should be set properly");
        assertEquals(2012, magazine.getPublicationYear(), "Publication year should be set properly");
        assertEquals(Magazine.STANDARD_AVAILABLE_COPIES, magazine.getAvailableCopies(), "Available copies are set automatically");
    }
    
    @Test
    void givenBookDetailsWithAvailableCopies_whenBookIsCreated_thanBookIsCorrectlyStoredInMemory() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 10);
        
        assertNotNull(magazine);
        assertEquals(10, magazine.getAvailableCopies(), "Available copies should be set");
    }
    
    @Test
    void givenIssnWithHyphens_whenMagazineIsCreated_thanIssnIsConvertedProperly() {
        String providedIssn = "0123-4567";
        String expectedIssn = "01234567";
        
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", providedIssn, 2012);
        
        assertEquals(expectedIssn, magazine.getIssn(), "ISSN should be properly converted");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"1234567", "123456789", "1234-5678-1", "123-4567"})
    void givenMagazineDetailsWithInvalidIssn_whenMagazineIsCreated_thanDomainExceptionIsThrown(String invalidIssn) {
        assertThrows(
                DomainException.class,
                () -> new Magazine("How to start using Spring Boot", "Baeldung", invalidIssn, 2012)
        );
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2025, -1})
    void giveMagazineDetailsWithInvalidPublicationYear_whenMagazineIsCreated_thanDomainExceptionIsThrown(int invalidPublicationYear) {
        assertThrows(
                DomainException.class,
                () -> new Magazine("How to start using Spring Boot", "Baeldung", "12345678", invalidPublicationYear)
        );
    }
    
    @Test
    void givenMagazineDetailsWithNullValues_whenMagazineIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new Magazine(null, null, null, 2012)
        );
    }
    
    @Test
    void givenMagazineDetailsWithEmptyValues_whenMagazineIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new Magazine(" ", " ", " ", 2012)
        );
    }
    
    @Test
    void givenMagazineWithOneAvailableCopy_whenMagazineIsLendOut_thanAvailableCopiesShouldReduceToZero() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 1);
        
        magazine.lendPublication();
        
        assertEquals(0, magazine.getAvailableCopies(), "Available copies should be zero");
    }
    
    @Test
    void givenMagazineWithNoAvailableCopy_whenMagazineIsLendOut_thanDomainExceptionIsThrown() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 0);
        
        assertThrows(
                DomainException.class,
                () -> magazine.lendPublication()
        );
        
        assertEquals(0, magazine.getAvailableCopies(), "Available copies should not have changed");
    }
    
    @Test
    void whenMagazineIsReturned_thanAvailableCopiesShouldIncrementByOne() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 0);
        
        magazine.returnPublication();
        
        assertEquals(1, magazine.getAvailableCopies(), "Available copies should increment to one");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"12345678", "87654321"})
    void equals_returnsTrueForEqualMagazines(String issn) {
        Magazine magazine1 = new Magazine("How to start using Spring Boot", "Baeldung", issn, 2012);
        Magazine magazine2 = new Magazine("How to start using Spring Boot", "Baeldung", issn, 2012);
        assertEquals(magazine1, magazine2);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"12345678", "87654321"})
    void equals_returnsFalseForDifferentMagazines(String issn) {
        Magazine magazine1 = new Magazine("How to start using Spring Boot", "Baeldung", issn, 2012);
        Magazine magazine2 = new Magazine("How to start using Spring Boot", "Another Editor", issn, 2012);
        assertNotEquals(magazine1, magazine2);
    }
    
    @Test
    void hashCode_returnsSameHashCodeForEqualMagazines() {
        Magazine magazine1 = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012);
        Magazine magazine2 = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012);
        assertEquals(magazine1.hashCode(), magazine2.hashCode());
    }
    
    @Test
    void hashCode_returnsDifferentHashCodeForDifferentMagazines() {
        Magazine magazine1 = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012);
        Magazine magazine2 = new Magazine("How to start using Spring Boot", "Another Editor", "12345678", 2012);
        assertNotEquals(magazine1.hashCode(), magazine2.hashCode());
    }
    
    @Test
    void toString_returnsCorrectStringRepresentation() {
        Magazine magazine = new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012);
        String expected = "Magazine{editor='Baeldung', issn='12345678', publication='Publication{title='How to start using Spring Boot', publicationYear=2012, availableCopies=12}'}";
        assertEquals(expected, magazine.toString());
    }
}
