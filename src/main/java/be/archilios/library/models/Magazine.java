package be.archilios.library.models;

import java.util.Objects;

import static be.archilios.library.utils.Validation.*;

public class Magazine extends Publication {
    public static final int STANDARD_AVAILABLE_COPIES = 12;
    
    private String editor;
    private String issn;
    
    public Magazine(String title, String editor, String issn, int publicationYear) {
        this(
                title,
                editor,
                issn,
                publicationYear,
                STANDARD_AVAILABLE_COPIES
        );
    }
    
    public Magazine(String title, String editor, String issn, int publicationYear, int availableCopies) {
        super(title, publicationYear, availableCopies);
        this.setEditor(editor);
        this.setIssn(issn);
    }
    
    public String getEditor() {
        return editor;
    }
    
    public String getIssn() {
        return issn;
    }
    
    public void setEditor(String editor) {
        validateNonEmptyString(editor, "Editor is required.");
        this.editor = editor;
    }
    
    public void setIssn(String issn) {
        issn = issn.replaceAll("-", "");
        validateIssn(issn, "Correct ISSN is required.");
        this.issn = issn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        
        Magazine magazine = (Magazine) o;
        return Objects.equals(getEditor(), magazine.getEditor()) && Objects.equals(getIssn(), magazine.getIssn());
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(getEditor());
        result = 31 * result + Objects.hashCode(getIssn());
        return result;
    }
    
    @Override
    public String toString() {
        return "Magazine{" +
                "editor='" + editor + '\'' +
                ", issn='" + issn + '\'' +
                ", publication='" + super.toString() + '\'' +
                '}';
    }
}
