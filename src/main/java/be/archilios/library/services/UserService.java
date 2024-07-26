package be.archilios.library.services;

import be.archilios.library.models.User;
import be.archilios.library.repositories.InMemoryUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    private final InMemoryUserRepository repository;
    
    public UserService(InMemoryUserRepository repository) {
        this.repository = repository;
    }
    
    public List<UserDto> getAllUsers() {
        return repository.findAllUsers()
                .stream()
                .map(UserDto::from)
                .toList();
    }
    
    public List<UserDto> getAllAdultUsers() {
        return repository.findAllUsersOlderThan(18)
                .stream()
                .map(UserDto::from)
                .toList();
    }
    
    public List<UserDto> getAllAdultsWithAgeBetween(int min, int max) {
        if (min > max) {
            throw new ServicesException("Minimum age cannot be greater than maximum age.");
        }
        if (min < 0 || max > 150) {
            throw new ServicesException("Invalid age range. Age must be between 0 and 150.");
        }
        
        return repository.findAllUsersWithAgeBetween(min, max)
                .stream()
                .map(UserDto::from)
                .toList();
    }
    
    public List<UserDto> getAllUsersContainingName(String name) {
        List<User> usersContainingName = repository.findAllUsersByName(name);
        if (usersContainingName.isEmpty()) {
            throw new ServicesException(String.format("User with name containing '%s' not found.", name));
        }
        
        return usersContainingName.stream()
                .map(UserDto::from)
                .toList();
    }
    
    public record UserDto(Long id, String name, int age, String email, String password) {
        public static UserDto from(User user) {
            return new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getPassword()
            );
        }
    }
}
