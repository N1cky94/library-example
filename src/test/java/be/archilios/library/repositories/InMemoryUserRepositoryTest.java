package be.archilios.library.repositories;

import be.archilios.library.models.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserRepositoryTest {
    @Test
    void findAllUsers_returnsAllUsersInDataStore() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        List<User> users = repository.findAllUsers();
        assertEquals(3, users.size());
        assertEquals("Nick Bauters", users.get(0).getName());
        assertEquals("Fynn Bauters", users.get(1).getName());
        assertEquals("Kelly de Lange", users.get(2).getName());
    }
    
    @Test
    void findAllUsers_returnsEmptyListWhenNoUsersInDataStore() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.clear();
        List<User> users = repository.findAllUsers();
        assertTrue(users.isEmpty());
    }
    
    @Test
    void clear_removesAllUsersFromDataStore() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        repository.clear();
        List<User> users = repository.findAllUsers();
        assertTrue(users.isEmpty());
    }
    
    @Test
    void clear_calledOnEmptyDataStore_doesNotThrowException() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        assertDoesNotThrow(repository::clear);
    }
    
    @Test
    void findAllAdults_returnsAllAdultUsersInDataStore() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        List<User> users = repository.findAllUsersOlderThan(18);
        assertEquals(2, users.size());
        assertEquals("Nick Bauters", users.get(0).getName());
        assertEquals("Kelly de Lange", users.get(1).getName());
    }
    
    @Test
    void findAllUsersWithAgeBetween_returnsAllUsersWithinAgeRangeInDataStore() {
        InMemoryUserRepository repository = new InMemoryUserRepository();
        List<User> users = repository.findAllUsersWithAgeBetween(1, 30);
        assertEquals(2, users.size());
        assertEquals("Fynn Bauters", users.get(0).getName());
        assertEquals("Kelly de Lange", users.get(1).getName());
    }
}
