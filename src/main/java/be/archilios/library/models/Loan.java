package be.archilios.library.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static be.archilios.library.utils.Validation.*;

public class Loan {
    private User user;
    private List<Publication> publications;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean processed = false;
    
    public Loan(User user, List<Publication> publications, LocalDate startDate, LocalDate endDate) {
        setUser(user);
        setPublications(publications);
        setStartDate(startDate);
        setEndDate(endDate);
        
        lendPublications();
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        validateExists(user, "User is required.");
        this.user = user;
    }
    
    public List<Publication> getPublications() {
        return publications;
    }
    
    private void setPublications(List<Publication> publications) {
        validateListExistsAndHoldsExistingData(publications, "Publication list and holding publications are required");
        this.publications = publications;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        validateExists(startDate, "Start day should be properly set");
        validateNonFutureDate(startDate, "Start Date should be today or before today");
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        validateExists(getStartDate(), "Start date should be set before setting end date");
        validateExists(endDate, "End date should be properly set");
        validateDateIsNotBefore(endDate, getStartDate(), "End Date should not be before Start Date");
        this.endDate = endDate;
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    private void setProcessed() {
        this.processed = true;
    }
    
    private void lendPublications() {
        if (isProcessed()) {
            return ;
        }
        
        publications.forEach(Publication::hasAvailableCopies);
        publications.forEach(Publication::lendPublication);
        
        setProcessed();
    }
    
    public void returnPublications() {
        publications.forEach(Publication::returnPublication);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Loan loan = (Loan) o;
        return isProcessed() == loan.isProcessed() && Objects.equals(getUser(), loan.getUser()) && Objects.equals(getPublications(), loan.getPublications()) && Objects.equals(getStartDate(), loan.getStartDate()) && Objects.equals(getEndDate(), loan.getEndDate());
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hashCode(getUser());
        result = 31 * result + Objects.hashCode(getPublications());
        result = 31 * result + Objects.hashCode(getStartDate());
        result = 31 * result + Objects.hashCode(getEndDate());
        result = 31 * result + Boolean.hashCode(isProcessed());
        return result;
    }
    
    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user +
                ", publications=" + publications +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", processed=" + processed +
                '}';
    }
}
