package be.archilios.library.models;

import java.util.Objects;

import static be.archilios.library.utils.Validation.*;

public class User {
    private String name;
    private String password;
    private String email;
    private int age;
    
    public User(String name, String password, String email, int age) {
        setName(name);
        setPassword(password);
        setEmail(email);
        setAge(age);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        validateNonEmptyString(name, "Name is required");
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        validatePassword(password, "Password must be at least 8 characters long");
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        validateEmail(email, "E-mail must be a valid email format");
        this.email = email;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        validateAge(age, "Age must be a positive integer between 0 and 101");
        this.age = age;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        User user = (User) o;
        return getAge() == user.getAge() && Objects.equals(getName(), user.getName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail());
    }
    
    @Override
    public int hashCode() {
        int result = Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getPassword());
        result = 31 * result + Objects.hashCode(getEmail());
        result = 31 * result + getAge();
        return result;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
