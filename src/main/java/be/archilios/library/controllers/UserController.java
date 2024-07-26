package be.archilios.library.controllers;

import be.archilios.library.models.DomainException;
import be.archilios.library.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (DomainException de) {
            return ResponseEntity.internalServerError()
                    .body(
                            new ErrorMessage(de.getMessage()));
        }
    }
    
    @GetMapping("/adults")
    public ResponseEntity<?> getAllAdults() {
        try {
            return ResponseEntity.ok(userService.getAllAdultUsers());
        } catch (DomainException de) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(de.getMessage()));
        }
    }
    
    record ErrorMessage(String error) { }
    
}
