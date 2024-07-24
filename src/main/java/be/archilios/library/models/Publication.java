package be.archilios.library.models;

import java.util.Objects;

import static be.archilios.library.utils.Validation.*;

public class Publication {
    private String title;
    private int publicationYear;
    private int availableCopies;
    
    public Publication(String title, int publicationYear, int availableCopies) {
        this.setTitle(title);
        this.setPublicationYear(publicationYear);
        this.setAvailableCopies(availableCopies);
    }
    
    public String getTitle() {
        return title;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public void setTitle(String title) {
        validateNonEmptyString(title, "Title is required.");
        this.title = title;
    }
    
    public void setPublicationYear(int publicationYear) {
        validateNonFutureYear(publicationYear, "Publication year cannot be in the future or before 1AD");
        this.publicationYear = publicationYear;
    }
    
    private void setAvailableCopies(int availableCopies) {
        validateNonNegativeNumber(availableCopies, "Cant have a negative amount of copies");
        this.availableCopies = availableCopies;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Publication that = (Publication) o;
        return getPublicationYear() == that.getPublicationYear() && getAvailableCopies() == that.getAvailableCopies() && Objects.equals(getTitle(), that.getTitle());
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitle());
        result = 31 * result + getPublicationYear();
        result = 31 * result + getAvailableCopies();
        return result;
    }
    
    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
