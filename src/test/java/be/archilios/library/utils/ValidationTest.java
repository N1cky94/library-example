package be.archilios.library.utils;

import be.archilios.library.models.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationTest {
    
    @Test
    void validateNonEmptyString_throwsExceptionForNullValue() {
        assertThrows(DomainException.class, () -> Validation.validateNonEmptyString(null, "Value cannot be null or empty"));
    }
    
    @Test
    void validateNonEmptyString_throwsExceptionForEmptyValue() {
        assertThrows(DomainException.class, () -> Validation.validateNonEmptyString("", "Value cannot be null or empty"));
    }
    
    @Test
    void validatePassword_throwsExceptionForShortPassword() {
        assertThrows(DomainException.class, () -> Validation.validatePassword("short", "Password must be at least 8 characters long"));
    }
    
    @Test
    void validateEmail_throwsExceptionForInvalidEmail() {
        assertThrows(DomainException.class, () -> Validation.validateEmail("invalidEmail", "Email must contain '@' and '.'"));
    }
    
    @Test
    void validateAge_throwsExceptionForNegativeAge() {
        assertThrows(DomainException.class, () -> Validation.validateAge(-1, "Age must be between 0 and 101"));
    }
    
    @Test
    void validateAge_throwsExceptionForAgeAbove101() {
        assertThrows(DomainException.class, () -> Validation.validateAge(102, "Age must be between 0 and 101"));
    }
    
    @Test
    void validateNonNegativeNumber_throwsExceptionForNegativeNumber() {
        assertThrows(DomainException.class, () -> Validation.validateNonNegativeNumber(-1, "Number must be non-negative"));
    }
    
    @Test
    void validateIsbn_throwsExceptionForInvalidIsbn() {
        assertThrows(DomainException.class, () -> Validation.validateIsbn("123456789012", "ISBN must be 13 characters long"));
    }
    
    @Test
    void validateIssn_throwsExceptionForInvalidIssn() {
        assertThrows(DomainException.class, () -> Validation.validateIssn("1234567", "ISSN must be 8 characters long"));
    }
    
    @Test
    void validateNonFutureYear_throwsExceptionForFutureYear() {
        assertThrows(DomainException.class, () -> Validation.validateNonFutureYear(LocalDate.now().getYear() + 1, "Year cannot be in the future"));
    }
    
    @Test
    void validateNonFutureDate_throwsExceptionForFutureDate() {
        assertThrows(DomainException.class, () -> Validation.validateNonFutureDate(LocalDate.now().plusDays(1), "Date cannot be in the future"));
    }
    
    @Test
    void validateDateIsNotBefore_throwsExceptionForDateBeforeNotBeforeDate() {
        assertThrows(DomainException.class, () -> Validation.validateDateIsNotBefore(LocalDate.now().minusDays(1), LocalDate.now(), "Date cannot be before the specified date"));
    }
    
    @Test
    void validateDateIsNotAfter_throwsExceptionForDateAfterNotAfterDate() {
        assertThrows(DomainException.class, () -> Validation.validateDateIsNotAfter(LocalDate.now().plusDays(1), LocalDate.now(), "Date cannot be after the specified date"));
    }
    
    @Test
    void validateExists_throwsExceptionForNullObject() {
        assertThrows(DomainException.class, () -> Validation.validateExists(null, "Object must exist"));
    }
    
    @Test
    void validateListExistsAndHoldsExistingData_throwsExceptionForNullList() {
        assertThrows(DomainException.class, () -> Validation.validateListExistsAndHoldsExistingData(null, "List must exist and hold existing data"));
    }
    
    @Test
    void validateListExistsAndHoldsExistingData_throwsExceptionForEmptyList() {
        assertThrows(DomainException.class, () -> Validation.validateListExistsAndHoldsExistingData(Arrays.asList(), "List must exist and hold existing data"));
    }
    
    @Test
    void validateListExistsAndHoldsExistingData_throwsExceptionForListWithNullElement() {
        assertThrows(DomainException.class, () -> Validation.validateListExistsAndHoldsExistingData(Arrays.asList(new Object(), null), "List must exist and hold existing data"));
    }
    
    @Test
    void validateNonEmptyList_throwsExceptionForEmptyList() {
        assertThrows(DomainException.class, () -> Validation.validateNonEmptyList(Arrays.asList(), "List must not be empty"));
    }
}