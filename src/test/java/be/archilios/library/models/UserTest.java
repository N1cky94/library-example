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
    @ValueSource(strings = {"short"}) // todo: Add these later: { "short", "noDIGITS", "NOLOWERCASE123", "nouppercase123"}
    void givenUserDetailsWithInvalidPassword_whenAUserIsCreated_thanDomainExceptionIsThrown(String password) {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", password, "nick@archilios.be", 33)
        );
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"nickarchilios.be", "nick@archiliosbe"})
    void givenUserDetailsWithInvalidEmail_whenUserIsCreated_thanDomainExceptionIsThrown(String password) {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", "1234abCD", password, 33)
        );
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 102})
    void givenUserDetailsWithInvalidNumber_whenUserIsCreated_thanDomainExceptionIsThrown(int age) {
        assertThrows(
                DomainException.class,
                () -> new User("Nick Bauters", "1234abCD", "nick@archilios.be", age)
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
    
    @ParameterizedTest
    @ValueSource(strings = {"Nick Bauters", "Kelly de Lange"})
    void equals_returnsTrueForEqualUsers(String name) {
        User user1 = new User(name, "1234abCD", "nick@archilios.be", 33);
        User user2 = new User(name, "1234abCD", "nick@archilios.be", 33);
        assertEquals(user1, user2);
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"Nick Bauters", "Kelly de Lange"})
    void equals_returnsFalseForDifferentUsers(String name) {
        User user1 = new User(name, "1234abCD", "nick@archilios.be", 33);
        User user2 = new User(name, "1234abCD", "kelly@archilios.be", 33);
        assertNotEquals(user1, user2);
    }
    
    @Test
    void hashCode_returnsSameHashCodeForEqualUsers() {
        User user1 = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        User user2 = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
    
    @Test
    void hashCode_returnsDifferentHashCodeForDifferentUsers() {
        User user1 = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        User user2 = new User("Kelly de Lange", "1234abCD", "nick@archilios.be", 33);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
    
    @Test
    void toString_returnsCorrectStringRepresentation() {
        User user = new User("Nick Bauters", "1234abCD", "nick@archilios.be", 33);
        String expected = "User{name='Nick Bauters', password='1234abCD', email='nick@archilios.be', age=33}";
        assertEquals(expected, user.toString());
    }
}
