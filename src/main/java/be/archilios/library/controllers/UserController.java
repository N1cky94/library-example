package be.archilios.library.controllers;

import be.archilios.library.models.DomainException;
import be.archilios.library.services.ServicesException;
import be.archilios.library.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/age/{min}/{max}")
    public ResponseEntity<?> getAllUsersBetweenAge(@PathVariable int min, @PathVariable int max) {
        try {
            return ResponseEntity.ok(userService.getAllAdultsWithAgeBetween(min, max));
        } catch (ServicesException se) {
            return ResponseEntity.badRequest().body(new ServiceError(400, se.getMessage()));
        }
    }
    
    record ErrorMessage(String error) {
        static ErrorMessage from(RuntimeException e) {
            return new ErrorMessage(e.getMessage());
        }
    }
    
}
