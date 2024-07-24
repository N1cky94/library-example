package be.archilios.library.models;

import java.util.Objects;

public class Magazine {
    private String title;
    private String editor;
    private String issn;
    private int publicationYear;
    
    public Magazine(String title, String editor, String issn, int publicationYear) {
        this.setTitle(title);
        this.setEditor(editor);
        this.setIssn(issn);
        this.setPublicationYear(publicationYear);
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getEditor() {
        return editor;
    }
    
    public String getIssn() {
        return issn;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setEditor(String editor) {
        this.editor = editor;
    }
    
    public void setIssn(String issn) {
        this.issn = issn;
    }
    
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Magazine magazine = (Magazine) o;
        return getPublicationYear() == magazine.getPublicationYear() && Objects.equals(getTitle(), magazine.getTitle()) && Objects.equals(getEditor(), magazine.getEditor()) && Objects.equals(getIssn(), magazine.getIssn());
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getEditor());
        result = 31 * result + Objects.hashCode(getIssn());
        result = 31 * result + getPublicationYear();
        return result;
    }
    
    @Override
    public String toString() {
        return "Magazine{" +
                "title='" + title + '\'' +
                ", editor='" + editor + '\'' +
                ", issn='" + issn + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
