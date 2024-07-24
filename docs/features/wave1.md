# First Feature Wave

> This repository is created to aid students 
> in a simple Java project that will travel through
> Java and Spring into a REST API application

## Content

1. [Register User](#story-1-register-user)
2. [Register Book](#story-2-register-book)

---

---
## Features / Stories

### Story 1: Register User

_As a librarian I want to register users in the system 
so that they can access library services and resources._

#### Acceptance Criteria
- [x] A User must have a name, password, email, and age.
- [x] E-mail must follow a valid email format.
- [x] The password must be at least 8 characters long.
- [x] Age must be a positive integer between 0 and 101.
- [x] When creating a User with invalid fields, an error message is given for each invalid field:
  - [x] “name”: “Name is required.”
  - [x] “password”: “Password must be at least 8 characters long.”
  - [x] “email”: “E-mail must be a valid email format.”
  - [x] “age”: “Age must be a positive integer between 0 and 101.”

#### Technical Requirements
- [x] Create a User class in src/java/be/_domain/project_/model containing the fields name, password, email and age.
- [x] Create a unit test “UserTest” in src/test/java/be/_domain/project_/model that covers happy paths and all validation scenarios.
- [x] Implement validation for each write method that changes the value of a field. The constructor of User uses these setters for instantiating these fields.
  - [x] To validate a required field of the type String (e.g. name), check that it is not null and that it does not consist only of spaces.
  - [x] For e-mail format validation: simply check if the string contains a “@” and “.”. Take a look at the documentation for Strings (and the “contains” method) on https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html
- [x] Implement a class DomainException that extends RuntimeException for handling validation errors. If validation fails, you should throw that error. Place the exception class in the package ucll.be.model.

---

### Story 2: Register Book
As a librarian,
I want to be able to register a book in the system, 
So that it becomes available for readers.

#### Acceptance Criteria
- [ ] A book must have a title, author, ISBN, and publication year.
- [ ] The title, author and ISBN should not be empty (null or only spaces).
  - [ ] An error “... is required.” should be given otherwise. 
- [ ] The publication year must be a positive number.
  - [ ] An error “Publication year must be a positive integer.” should be given otherwise.
- [ ] The publication year cannot be in the future.
  - [ ] An error “Publication year cannot be in the future.” should be given
  otherwise.

#### Technical Requirements
- [ ] Create a Book class with the specified fields.
- [ ] Implement validation within the setters or the constructor of the class.
  - [ ] An ISBN is a String that contains 13 digits. Example: 978-0-545-01022-1 
  - [ ] For publication year validation, have a look at the java Year class (and its now() method): https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Year.html
- [ ] Write unit tests covering both the happy and unhappy cases.

---