package be.archilios.library.repositories;

import be.archilios.library.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository {
    private final static List<User> usersDataStore = new ArrayList<>();
    
    public InMemoryUserRepository() {
        usersDataStore.add(new User(1L, "Nick Bauters", "1234abCD9", "nick@archilios.be", 33));
        usersDataStore.add(new User(2L, "Fynn Bauters", "1234abCV9", "fynn@archilios.be", 2));
        usersDataStore.add(new User(3L, "Kelly de Lange", "8765zyXW0", "kelly@archilios.be", 26));
    }
    
    public List<User> findAllUsers() {
        return usersDataStore;
    }
    
    public List<User> findAllUsersOlderThan(int age) {
        return usersDataStore.stream().filter(u -> u.getAge() >= age).toList();
    }
    
    public void clear() {
        usersDataStore.clear();
    }
}
