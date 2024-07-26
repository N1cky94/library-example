package be.archilios.library.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DomainExceptionTest {
    @Test
    void domainException_withNoArgsConstructor_shouldCreateException() {
        DomainException exception = new DomainException();
        assertNotNull(exception);
    }
    
    @Test
    void domainException_withMessageConstructor_shouldStoreMessage() {
        String message = "Error occurred";
        DomainException exception = new DomainException(message);
        assertEquals(message, exception.getMessage());
    }
    
    @Test
    void domainException_withCauseConstructor_shouldStoreCause() {
        Throwable cause = new Throwable("Cause");
        DomainException exception = new DomainException(cause);
        assertEquals(cause, exception.getCause());
    }
    
    @Test
    void domainException_withMessageAndCauseConstructor_shouldStoreMessageAndCause() {
        String message = "Error occurred";
        Throwable cause = new Throwable("Cause");
        DomainException exception = new DomainException(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
    
    @Test
    void domainException_withAllArgsConstructor_shouldStoreAllFields() {
        String message = "Error occurred";
        Throwable cause = new Throwable("Cause");
        DomainException exception = new DomainException(message, cause, true, false);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
