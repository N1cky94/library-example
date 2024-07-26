package be.archilios.library.repositories;

import be.archilios.library.models.Book;
import be.archilios.library.models.Magazine;
import be.archilios.library.models.Publication;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Repository
public class InMemoryPublicationRepository {
    private static final List<Publication> publications = new ArrayList<>();
    
    public InMemoryPublicationRepository() {
        if (publications.isEmpty()) {
            resetDataStore();
        }
    }
    
    public List<Publication> findPublicationsByTitleAndType(String title, String type) {
        var streamOfPublis = publications.stream();
        if (title != null && !title.isEmpty()) {
            streamOfPublis = streamOfPublis.filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()));
        }
        if (type != null && !type.isEmpty()) {
            streamOfPublis = streamOfPublis.filter(p -> p.getClass().getName().toLowerCase().contains(type.toLowerCase()));
        }
        return streamOfPublis.toList();
    }
    
    public List<Publication> findAllPublications() {
        return publications;
    }
    
    public void resetDataStore() {
        clearDataStore();
        
        publications.addAll(
                Arrays.asList(
                        new Book("Clean Code", "Robert C. Martin", "1234567891234", 2009),
                        new Book("Clean Coder", "Robert C. Martin", "1234567891235", 2012),
                        new Book("Clean Architecture", "Robert C. Martin", "1234567891236", 2022),
                        new Magazine("Cleaning up my code", "Baeldung", "1234-5678", 2016),
                        new Magazine("Union Architecture", "Baeldung", "1234-5679", 2023),
                        new Book("Pragmatic Programmer", "Hunt & Thomas", "1234567891244", 2002)
                )
        );
    }
    
    public void clearDataStore() {
        publications.clear();
    }
}
