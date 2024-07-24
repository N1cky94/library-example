# First Feature Wave

> This repository is created to aid students 
> in a simple Java project that will travel through
> Java and Spring into a REST API application

## Content

These are the stories and features that make version **V0.0.N**.

1. [Register User](#story-1-register-user)
2. [Register Book](#story-2-register-book)
3. [Register Magazine](#story-3-register-magazine)
4. [Publications](#story-4-publications)

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
- [x] A book must have a title, author, ISBN, and publication year.
- [x] The title, author and ISBN should not be empty (null or only spaces).
  - [x] An error “... is required.” should be given otherwise. 
- [x] The publication year must be a positive number.
  - [x] An error “Publication year must be a positive integer.” should be given otherwise.
- [x] The publication year cannot be in the future.
  - [x] An error “Publication year cannot be in the future.” should be given
  otherwise.

#### Technical Requirements
- [x] Create a Book class with the specified fields.
- [x] Implement validation within the setters or the constructor of the class.
  - [x] An ISBN is a String that contains 13 digits. Example: 978-0-545-01022-1 
  - [x] For publication year validation, have a look at the java Year class (and its now() method): https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/Year.html
- [x] Write unit tests covering both the happy and unhappy cases.

---

### Story 3: Register Magazine
As a librarian,
I want to be able to register a magazine in the system, 
So that it becomes available for readers.

#### Acceptance Criteria
- [x] A magazine must have a title, editor, ISSN (not ISBN!), and publication year.
- [x] The title, editor and ISSN should not be empty.
  - [x] An error “... is required.” should be given otherwise. 
- [x] The publication year must be a positive number.
  - [x] An error “Publication year must be a positive integer.” should be given otherwise.
- [x] The publication year cannot be in the future.
  - [x] An error “Publication year cannot be in the future.” should be given otherwise.

#### Technical Requirements
- [x] Create a Magazine class with the specified fields.
- [x] Implement validation within the setters or the constructor of the class.
- [x] Write unit tests covering both the happy and unhappy cases.

---

### Story 4: Publications

To streamline the management of different types of publications within the library system, 
we need to ensure that both Book and Magazine entities share common attributes and behaviors. 
This story aims to introduce a base class named Publication from which both Book and Magazine 
will inherit. This approach promotes code reuse and polymorphism, making the system more 
maintainable and scalable.

#### Acceptance Criteria
- [ ] The Publication class must define common fields that are shared by both books and magazines, such as title and publicationYear.
- [ ] Book and Magazine must extend Publication class and use its constructor for initializing common fields.
- [ ] Specific fields unique to Book (author, ISBN) and Magazine (editor, ISSN) remain in their respective classes.

#### Technical Requirements
- [ ] Create an abstract class Publication.
- [ ] Publication year is number.
- [ ] Implement getters and setters for the common fields in the Publication class,  ensuring data validation where applicable.
- [ ] Modify the Book and Magazine classes to extend Publication, removing any duplicate  fields or methods that are now inherited from Publication.
- [ ] Ensure that constructors in Book and Magazine call the superclass (Publication) constructor to initialize common fields.
- [ ] Upon registering a new Book or Magazine, an initial availableCopies is passed to the  constructor.
- [ ] Note: This refactoring should not alter the external behavior of the Book and  Magazine. Your unit tests must continue to run successfully throughout this process.

---