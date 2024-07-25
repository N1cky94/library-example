package be.archilios.library.utils;

import be.archilios.library.models.DomainException;

import java.time.LocalDate;
import java.util.List;

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
    
    static void validateNonFutureDate(LocalDate date, String message) {
        validateDateIsNotAfter(date, LocalDate.now(), message);
    }
    
    static void validateDateIsNotBefore(LocalDate dateToCheck, LocalDate notBeforeDate, String message) {
        if (dateToCheck.isBefore(notBeforeDate)) {
            throwDomainException(message);
        }
    }
    
    static void validateDateIsNotAfter(LocalDate dateToCheck, LocalDate notAfterDate, String message) {
        if (dateToCheck.isAfter(notAfterDate)) {
            throwDomainException(message);
        }
    }
    
    static void validateExists(Object o, String message) {
        if (o == null) {
            throwDomainException(message);
        }
    }
    
    static <T> void validateListExistsAndHoldsExistingData(List<T> objects, String message) {
        validateExists(objects, message);
        validateNonEmptyList(objects, message);
        objects.forEach(o -> validateExists(o, message));
    }
    
    static <T> void validateNonEmptyList(List<T> list, String message) {
        if (list.isEmpty()) {
            throwDomainException(message);
        }
    }
}
