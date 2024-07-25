package be.archilios.library.repositories;

import be.archilios.library.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository {
    private final static List<User> usersDataStore = new ArrayList<>();
    
    public InMemoryUserRepository() {
        usersDataStore.add(new User("Nick Bauters", "1234abCD9", "nick@archilios.be", 33));
        usersDataStore.add(new User("Kelly de Lange", "8765zyXW0", "kelly@archilios.be", 26));
    }
    
    public List<User> findAllUsers() {
        return usersDataStore;
    }
}
