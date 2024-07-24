package be.archilios.library.utils;

import be.archilios.library.models.DomainException;

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
}
