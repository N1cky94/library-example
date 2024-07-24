package be.archilios.library.utils;

import be.archilios.library.models.DomainException;

import java.time.LocalDate;

public interface Validation {
    static void throwDomainException(String message) {
        throw new DomainException(message);
    }
    
    static void validateNonEmptyString(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throwDomainException(message);
        }
    }
    
    static void validatePassword(String password, String message) {
        validateNonEmptyString(password, message);
        if (password.length() < 8) {
            throwDomainException(message);
        }
    }
    
    static void validateEmail(String email, String message) {
        validateNonEmptyString(email, message);
        if ( !(email.contains("@") && email.contains(".")) ) {
            throwDomainException(message);
        }
    }
    
    static void validateAge(int age, String message) {
        if (age < 0 || age > 101) {
            throwDomainException(message);
        }
    }
    
    static void validateNonNegativeNumber(int number, String message) {
        if (number < 0) {
            throwDomainException(message);
        }
    }
    
    static void validateIsbn(String isbn, String message) {
        if (!(isbn.length() == 13)) {
            throwDomainException(message);
        }
    }
    
    static void validateIssn(String issn, String message) {
        if (!(issn.length() == 8)) {
            throwDomainException(message);
        }
    }
    
    static void validateNonFutureYear(int year, String message) {
        if (year < 0 || year > LocalDate.now().getYear()) {
            throwDomainException(message);
        }
    }
}
