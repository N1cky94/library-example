# First Feature Wave

> This repository is created to aid students
> in a simple Java project that will travel through
> Java and Spring into a REST API application

## Content

These are the stories and features that make version **V0.1.N**.
We are now introducing the web-API

1. [Spring Boot](#framework-upgrade-implement-spring-boot-and-web)
2. [Retrieve all users over web-API](#story-6-retrieve-all-users)
3. [Retrieve all adult users web-API](#story-7-retrieve-all-adults)
4. [Retrieve all users within age range](#story-8-retrieve-all-users-within-age-range)
5. [Retrieve all users with name containing](#story-9-filter-users-by-name)
6. [Retrieve all publications by name or type](#story-10-filter-publications-by-title-and-type)
7. [Retrieve all publications with available copies](#story-11-retrieve-all-publications-with-available-copies)
8. [Unit testing](#feature-unit-test-clean-up-and-refactoring)
9. [Retrieve all Loans from a User]()
10. [Register a User]()
11. [Update a User]()
12. [Deleting a Loan]()
13. [Deleting a User]()
14. [Creating Integration Tests]()
15. [Handling Exceptions]()
16. [Using Hibernate Validation Library]()

---

---
## Features / Stories

### Framework upgrade: Implement Spring Boot and Web

We are going to start using Spring with some dependencies.

#### Acceptance Criteria
- [x] Add Spring Boot 3 dependencies
  - [x] Actuator
  - [x] Web
  - [x] Devtools
  - [x] test
- [x] Configure Spring in the application

### Story 6: Retrieve all users

As an administrator,
I want to retrieve a list of all registered users,
So that I can see all users currently in the system.

#### API

|         method | value                                                                                                                                     |
|---------------:|:------------------------------------------------------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                                                                       |
|            URL | http://localhost:8080/users                                                                                                               |
| Path Variables | None                                                                                                                                      |
|   Request Body | None                                                                                                                                      |
| Output success | - A list of User objects (JSON)<br> - fields for each user: name, age, email, password <br> - The list may be empty if no users are found |
|   Output error | None                                                                                                                                      |

#### Acceptance Criteria
- [x] The endpoint returns a list of all users registered in the system with their details.
- [x] Each user's information includes name, age, email, password.
- [x] If no users are registered, the API returns an empty list.
- [x] In case of a server error, an appropriate error message is returned.

#### Technical Requirements:
- [x] Add a new endpoint in UserRestController to handle the GET request.
- [x] Test all the (un)happy paths in the service. No error handling is required in the controller yet.
- [x] The endpoint is tested with **HTTP Client**.

---

### Story 7: Retrieve all adults

As an administrator,
I want to retrieve a list of all adult users,
So that I can see all adults currently in the system.

#### API

|         method | value                                                                                                                                         |
|---------------:|:----------------------------------------------------------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                                                                           |
|            URL | http://localhost:8080/users/adults                                                                                                            |
| Path Variables | None                                                                                                                                          |
|   Request Body | None                                                                                                                                          |
| Output success | - A list of User objects (JSON)<br> - fields for each user: id, name, age, email, password <br> - The list may be empty if no users are found |
|   Output error | None                                                                                                                                          |

#### Acceptance Criteria

- [x] The endpoint returns a list of all users with age >= 18.
- [x] Each user's information includes id, name, age, email, password.
- [x] If no users are registered, the API returns an empty list.

#### Technical Requirements:

- [x] Add a new endpoint in UserRestController to handle the GET request.
- [x] The logic to get all adult users should mimic a database query with a “where age >= 18” clause, so this needs to be implemented in the repository (not in the service).
  - [x] Create a method usersOlderThan(int age) in the UserRepository that contains the logic to filter the list on the given criteria (not in the service).
- [x] Create a method in UserService to fulfill the business scenario of getting all adult users: getAllAdultUsers.
- [x] Users should have an implementation of an ID within the repository layer
- [x] Test all the (un)happy paths in the service. No error handling is required in the controller yet.
- [x] The endpoint is tested with **HTTP Client**.

---

### Story 8: Retrieve all users within age range

#### API

|         method | value                                                                                                                                         |
|---------------:|:----------------------------------------------------------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                                                                           |
|            URL | http://localhost:8080/users/age/{min}/{max}                                                                                                   |
| Path Variables | - min (int): The minimum age boundary for the search<br>- max (int): The maximum age boundary for the search                                  |
|   Request Body | None                                                                                                                                          |
| Output success | - A list of User objects (JSON)<br> - fields for each user: id, name, age, email, password <br> - The list may be empty if no users are found |
|   Output error | None                                                                                                                                          |

#### Acceptance Criteria

- [x] Users returned must have an age greater than or equal to min and less than or equal to max.
- [x] The system must validate that `min` is less than or equal to `max`.
  - [x] Error: "Minimum age cannot be greater than maximum age."
- [x] The system should also validate that the provided age range is between 0 and 150 years old.
  - [x] Error: "Invalid age range. Age must be between 0 and 150."
- [x] If no users fall within the specified age range, the system should return an empty JSON array.

#### Technical Requirements
- [x] Implement a method in the “UserService” that leverages the “UserRepository” to find users by age.
- [x] Validation logic for the age range should be encapsulated within the service method, throwing ServiceExceptions as necessary for invalid inputs.
- [x] Add endpoint `/users/search/age/{min}/{max}` in “UserController”
- [x] Create a ServiceException class that extends RuntimeException in the service package.
- [x] Test all the (un)happy paths in the service. No error handling is required in the controller yet.
- [x] The endpoint is tested with **HTTP Client**.

---

### Story 9: Filter users by name

As a librarian,
I want to be able to filter users by name,
So that I can easily find specific users in the database

#### API

|         method | value                                                                                      |
|---------------:|:-------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                        |
|            URL | http://localhost:8080/users?name=                                                          |
| Path Variables | None                                                                                       |
|   Request Body | - name (string, optional): the potential name of a user                                    |
| Output success | - A list of User objects (JSON)<br> - fields for each user: id, name, age, email, password |
|   Output error | None                                                                                       |

#### Acceptance Criteria
- [x] If a name is provided as a query parameter, the system should return all users that contain the request parameter "name" characters in their name (partial matching). E.g. “Doe” should return “John Doe”, “Jane Doe” and “Sarah Doe”.
- [x] An error should be thrown if no users are found with the specified name.
- [x] The name request parameter is optional. If it is empty, all users are returned, just as before.
#### Technical Requirements
- [x] Extend the existing endpoint `/users` in UserRestController. Do not create a new endpoint.
- [x] You can make RequestParam optional by using the `required` attribute.
- [x] Create a new method `usersByName()` in the UserRepository.
- [x] UserService should have a separate method to get users by name that throws an exception if the repository returns an empty list.
- [x] Test all (un)happy scenario’s in UserServiceTest.
- [x] No error handling is required in the controller yet.
- [x] The endpoint is tested with **HTTP Client**.

---

### Story 10: Filter publications by title and type

As a librarian,
I want to filter publications by title and type,
So that I can easily find specific publications in the library.

#### API

|         method | value                                                                                                                                                      |
|---------------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                                                                                        |
|            URL | http://localhost:8080/publications                                                                                                                         |
| Path Variables | None                                                                                                                                                       |
|   Request Body | - title (String, optional): The potential title of a publication<br>- type (String, optional): Returns the type of publication (e.g. `Book` or `Magazine`) |
| Output success | - A list of Publication objects (JSON)<br> - All fields of Book or Magazine are returned                                                                   |
|   Output error | An empty list if no publications match the filter criteria                                                                                                 |

#### Acceptance Criteria

- [x] The system should return a list of publications that match all provided criteria.
  - [x] Title is partially matched (not exactly).
  - [x] All details of a publication (Book or Magazine) are returned.
- [x] When no publications match the provided filter criteria, the system should return an empty list.
- [x] The system should allow filtering by any combination of provided criteria (title, type), including none, one, two. If no filter is present, all publications are returned.

#### Technical Requirements
- [x] PublicationRestController should have 1 mapping for the path `/publications`.
- [x] PublicationService should have 1 method `findPublicationsByTitleAndType` .
- [x] PublicationRepository should be responsible for checking the type.
- [x] In PublicationRepository, add 1 list of type `Publication` that represent the database for publications.
- [ ] Ensure that the PublicationService is covered with unit tests verifying the functionality for various combinations of filter criteria.
- [x] The endpoint is tested with **HTTP Client**.

---

### Story 11: Retrieve all publications with available copies

As a librarian,
I want to retrieve all publications that have a specified number of available copies,
So that I can quickly find publications that meet the stock criteria for lending or reference.

#### API

|         method | value                                                                                           |
|---------------:|:------------------------------------------------------------------------------------------------|
|   Request Type | GET                                                                                             |
|            URL | http://localhost:8080/publications/stock/{availableCopies}                                      |
| Path Variables | - AvailableCopies (integer)                                                                     |
|   Request Body | None                                                                                            |
| Output success | - A list of Publication objects (JSON)<br> - Publication info: id, title, type, availableCopies |
|   Output error | An empty list if no publications match the filter criteria                                      |

#### Acceptance Criteria

- [ ] The system should return a list of publications that have equal or more than the specified number of available copies.
- [ ] All the details of each publication (book or magazine) are returned.
- [ ] When no publications match the available copies criterion, the system should return an empty list.

---

### Feature: Unit test clean-up and refactoring

As a developer 
I want to write unit tests and refactor all business logic 
So we keep the code clean and maintainable

#### Acceptance Criteria

- [ ] All business logic is tested (happy and unhappy)
- [ ] All self-written repository logic is tested (happy and unhappy)
- [ ] All unneeded tests from the domain is removed
- [ ] Refactoring of the business logic for readability and testability
- [ ] Introduction of Interfaces for repositories and services
- [ ] Protection of domain data by closing encapsulation 