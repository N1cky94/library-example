package be.archilios.library.services;

import be.archilios.library.models.Book;
import be.archilios.library.models.Magazine;
import be.archilios.library.models.Publication;
import be.archilios.library.repositories.InMemoryPublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {
    private final InMemoryPublicationRepository repository;
    
    public PublicationService(InMemoryPublicationRepository repository) {
        this.repository = repository;
    }
    
    public List<PublicationDto> getPublicationsByTitleAndType(String title, String type) {
        if ((title == null && type == null)) {
            return repository.findAllPublications().stream()
                    .map(PublicationDto::from)
                    .toList();
        }
        
        return repository.findPublicationsByTitleAndType(title, type).stream()
                .map(PublicationDto::from)
                .toList();
    }
    
    public record PublicationDto(
            String type,
            Long id,
            String title,
            String author,
            String code,
            int publicationYear,
            int availableCopies
    ) {
        public static PublicationDto from(Publication publication) {
            if (publication instanceof Book) {
                return from((Book) publication);
            } else {
                return from((Magazine) publication);
            }
        }
        
        public static PublicationDto from(Book publication) {
            return new PublicationDto(
                    "book",
                    null,
                    publication.getTitle(),
                    publication.getAuthor(),
                    publication.getIsbn(),
                    publication.getPublicationYear(),
                    publication.getAvailableCopies()
            );
        }
        
        public static PublicationDto from(Magazine publication) {
            return new PublicationDto(
                    "magazine",
                    null,
                    publication.getTitle(),
                    publication.getEditor(),
                    publication.getIssn(),
                    publication.getPublicationYear(),
                    publication.getAvailableCopies()
            );
        }
    }
}
