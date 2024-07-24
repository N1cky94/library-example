package be.archilios.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    void givenCorrectUserDetails_whenANewUserIsCreated_thanAUserIsCorrectlyStoredInMemory() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        
        assertNotNull(user);
        assertEquals("Nick Bauters", user.getName(), "Name should be set properly");
        assertEquals("1234abCD", user.getPassword(), "Password should be set properly");
        assertEquals("nick@archilios.be", user.getEmail(), "Email should be set properly");
        assertEquals(33, user.getAge(), "Age should be set properly");
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"short", "noDIGITS", "NOLOWERCASE123", "nouppercase123"})
    void givenUserDetailsWithInvalidPassword_whenAUserIsCreated_thanDomainExceptionIsThrown(String password) {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", password, "nick@archilios.be", 33)
        );
    }
    
    @Test
    void givenUserDetailsWithInvalidEmail_whenUserIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", "1234abCD", "nickarchilios.be", 33)
        );
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 102})
    void givenUserDetailsWithInvalidNumber_whenUserIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", "1234abCD", "nickarchilios.be", 33)
        );
    }
    
    @Test
    void givenUserDetailsWithNullValues_whenUserIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new User(null, null, null, 0)
        );
    }
    
    @Test
    void givenUserDetailsWithEmptyValues_whenUserIsCreated_thanDomainExceptionIsThrown() {
        assertThrows(
                DomainException.class,
                () -> new User(" ", " ", " ", 33)
        );
    }
}
