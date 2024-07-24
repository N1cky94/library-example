# First Feature Wave

> This repository is created to aid students 
> in a simple Java project that will travel through
> Java and Spring into a REST API application

## Content

1. [Register User](#story-1-register-user)
2. 

## Features / Stories

### Story 1: Register User

_As a librarian I want to register users in the system 
so that they can access library services and resources._

#### Acceptance Criteria
- [ ] A User must have a name, password, email, and age.
- [ ] E-mail must follow a valid email format.
- [ ] The password must be at least 8 characters long.
- [ ] Age must be a positive integer between 0 and 101.
- [ ] When creating a User with invalid fields, an error message is given for each invalid field:
  - [ ] “name”: “Name is required.”
  - [ ] “password”: “Password must be at least 8 characters long.”
  - [ ] “email”: “E-mail must be a valid email format.”
  - [ ] “age”: “Age must be a positive integer between 0 and 101.”

#### Technical Requirements
- [ ] Create a User class in src/java/be/ucll/model containing the fields name, password, email and age.
- [ ] Create a unit test “UserTest” in src/test/java/be/ucll/model that covers happy paths and all validation scenarios.
- [ ] Implement validation for each write method that changes the value of a field. The constructor of User uses these setters for instantiating these fields.
  - [ ] To validate a required field of the type String (e.g. name), check that it is not null and that it does not consist only of spaces.
  - [ ] For e-mail format validation: simply check if the string contains a “@” and “.”. Take a look at the documentation for Strings (and the “contains” method) on https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html
- [ ] Implement a class DomainException that extends RuntimeException for handling validation errors. If validation fails, you should throw that error. Place the exception class in the package ucll.be.model.