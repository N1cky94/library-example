package be.archilios.library.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {
    
    @Test
    void givenValidLoanDetails_whenLoanIsCreated_LoanIsStoredCorrectlyInMemory() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        Loan loan = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        
        assertNotNull(loan);
        assertEquals(user, loan.getUser(), "User should be set properly");
        assertEquals(publications, loan.getPublications(), "List of publications should be set properly");
        assertEquals(LocalDate.now(), loan.getStartDate(), "Start date should be today");
        assertEquals(LocalDate.now().plusDays(15), loan.getEndDate(), "End date should be 15 days from now");
        assertTrue(loan.isProcessed(), "Loan should be processed");
    }
    
    @Test
    void givenLoanWithNullValues_whenLoanIsCreated_thanDomainExceptionIsThrown() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        assertThrows(
                DomainException.class,
                () -> new Loan(null, publications, LocalDate.now(), LocalDate.now().plusDays(15))
        );
        assertThrows(
                DomainException.class,
                () -> new Loan(user, null, LocalDate.now(), LocalDate.now().plusDays(15))
        );
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, null, LocalDate.now().plusDays(15))
        );
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now(), null)
        );
        
    }
    
    @Test
    void givenLoanWithEmptyPublicationsList_whenLoanIsCreated_thanDomainExceptionIsThrown() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15))
        );
    }
    
    @Test
    void givenValidStartDate_whenLoanIsCreated_thanStoreLoanInMemory() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        Loan loanOfToday = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        Loan loanOfYesterday = new Loan(user, publications, LocalDate.now().minusDays(1), LocalDate.now().plusDays(14));
        
        assertEquals(LocalDate.now(), loanOfToday.getStartDate(), "Start day should be able to be today");
        assertEquals(LocalDate.now().minusDays(1), loanOfYesterday.getStartDate(), "Start day should be able to be in the past");
    }
    
    @Test
    void givenInvalidStartDate_whenLoanIsCreated_thanDomainExceptionIsThrown() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now().plusDays(1), LocalDate.now().plusDays(25))
        );
    }
    
    @Test
    void givenEndDateBeforeStartDate_whenLoanIsCreated_thanDomainExceptionIsThrown() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now(), LocalDate.now().minusDays(1))
        );
    }
    
    @Test
    void givenValidLoan_whenPublicationsAreCheckedOut_thanAvailableCopiesOfAllPublicationsReduceByOne() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012));
        
        Loan loan = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        
        assertEquals(Book.STANDARD_AVAILABLE_COPIES - 1, loan.getPublications().get(0).getAvailableCopies(), "Book publication should have one less copy");
        assertEquals(Magazine.STANDARD_AVAILABLE_COPIES - 1, loan.getPublications().get(1).getAvailableCopies(), "Magazine publication should have one less copy");
        assertTrue(loan.isProcessed(), "Loan has been processed");
    }
    
    @Test
    void givenValidLoan_whenPublicationsAreReturned_thanAvailableCopiesOfAllPublicationsIncreaseByOne() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, Book.STANDARD_AVAILABLE_COPIES));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, Magazine.STANDARD_AVAILABLE_COPIES));
        
        Loan loan = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        loan.returnPublications();
        
        assertEquals(Book.STANDARD_AVAILABLE_COPIES, loan.getPublications().get(0).getAvailableCopies(), "Book publication should have one extra available copie");
        assertEquals(Magazine.STANDARD_AVAILABLE_COPIES, loan.getPublications().get(1).getAvailableCopies(), "Magazine publication should have one extra available copie");
    }
    
    @Test
    void givenValidLoanWithPublicationsWithoutAvailableCopies_whenPublicationsAreAddedToLoan_thanDomainExceptionIsThrown() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 0));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 0));
        
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15))
        );
    }
    
    @Test
    void givenValidLoanWithPublicationsWhereOnePublicationHasNoAvailableCopies_whenPublicationsAreAddedToLoan_thanDomainExceptionIsThrownWithNoChangeToAvailableCopies() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = new ArrayList<>();
        publications.add(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009, 1));
        publications.add(new Magazine("How to start using Spring Boot", "Baeldung", "12345678", 2012, 0));
        
        assertThrows(
                DomainException.class,
                () -> new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15))
        );
        
        assertEquals(1, publications.get(0).getAvailableCopies(), "Book Available Copies should be left unchanged");
        assertEquals(0, publications.get(1).getAvailableCopies(), "Magazine available copies should be left unchanged");
    }
    
    @Test
    void equals_returnsTrueForEqualLoans() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = List.of(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        Loan loan1 = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        Loan loan2 = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        assertEquals(loan1, loan2);
    }
    
    @Test
    void equals_returnsFalseForDifferentLoans() {
        User user1 = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        User user2 = new User("Kelly de Lange", "8765zyXW", "kelly@archilios.be", 26);
        List<Publication> publications = List.of(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        Loan loan1 = new Loan(user1, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        Loan loan2 = new Loan(user2, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        assertNotEquals(loan1, loan2);
    }
    
    @Test
    void hashCode_returnsSameHashCodeForEqualLoans() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = List.of(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        Loan loan1 = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        Loan loan2 = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        assertEquals(loan1.hashCode(), loan2.hashCode());
    }
    
    @Test
    void hashCode_returnsDifferentHashCodeForDifferentLoans() {
        User user1 = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        User user2 = new User("Kelly de Lange", "8765zyXW", "kelly@archilios.be", 26);
        List<Publication> publications = List.of(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        Loan loan1 = new Loan(user1, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        Loan loan2 = new Loan(user2, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        assertNotEquals(loan1.hashCode(), loan2.hashCode());
    }
    
    @Test
    void toString_returnsCorrectStringRepresentation() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        List<Publication> publications = List.of(new Book("Clean Code", "Robert C. Martin", "9780132350884", 2009));
        Loan loan = new Loan(user, publications, LocalDate.now(), LocalDate.now().plusDays(15));
        String expected = "Loan{user=User{name='Nick Bauters', password='1234abCD', email='nick@archilios.be', age=33}, publications=[Book{author='Robert C. Martin', isbn='9780132350884', publication='Publication{title='Clean Code', publicationYear=2009, availableCopies=4}'}], startDate=2024-07-26, endDate=2024-08-10, processed=true}";
        assertEquals(expected, loan.toString());
    }
    
}
