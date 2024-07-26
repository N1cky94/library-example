package be.archilios.library.controllers;

import be.archilios.library.models.DomainException;
import be.archilios.library.services.ServicesException;
import be.archilios.library.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam(required = false) String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.ok(userService.getAllUsers());
            } else {
                return ResponseEntity.ok(userService.getAllUsersContainingName(name));
            }
        } catch (DomainException de) {
            return ResponseEntity.internalServerError().body(ErrorMessage.from(de));
        } catch (ServicesException se) {
            return ResponseEntity.badRequest().body(ServiceError.from(404, se));
        }
        
    }
    
    @GetMapping("/adults")
    public ResponseEntity<?> getAllAdults() {
        try {
            return ResponseEntity.ok(userService.getAllAdultUsers());
        } catch (DomainException de) {
            return ResponseEntity.internalServerError().body(ErrorMessage.from(de));
        }
    }
    
    @GetMapping("/age/{min}/{max}")
    public ResponseEntity<?> getAllUsersBetweenAge(@PathVariable int min, @PathVariable int max) {
        try {
            return ResponseEntity.ok(userService.getAllAdultsWithAgeBetween(min, max));
        } catch (ServicesException se) {
            return ResponseEntity.badRequest().body(ServiceError.from(400, se));
        }
    }
    
    record ErrorMessage(String error) {
        static ErrorMessage from(RuntimeException e) {
            return new ErrorMessage(e.getMessage());
        }
    }
    
}
