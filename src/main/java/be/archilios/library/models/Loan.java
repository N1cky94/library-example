package be.archilios.library.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Loan {
    private User user;
    private List<Publication> publications;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean processed = false;
    
    public Loan(User user, List<Publication> publications, LocalDate startDate, LocalDate endDate) {
    
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Publication> getPublications() {
        return publications;
    }
    
    private void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    private void setProcessed(boolean processed) {
        this.processed = processed;
    }
    
    public void returnPublications() {
    
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
