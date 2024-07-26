package be.archilios.library.controllers;

import be.archilios.library.services.PublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    
    private final PublicationService service;
    
    public PublicationController(PublicationService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<?> getPublications(@RequestParam(required = false) String title, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(service.getPublicationsByTitleAndType(title, type));
    }
}
