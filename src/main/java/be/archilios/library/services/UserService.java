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
    
    public record UserDto(String name, int age, String email, String password) {
        public static UserDto from(User user) {
            return new UserDto(
                    user.getName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getPassword()
            );
        }
    }
}
