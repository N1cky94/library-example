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

- [ ] Users returned must have an age greater than or equal to min and less than or equal to max.
- [ ] The system must validate that `min` is less than or equal to `max`.
  - [ ] Error: "Minimum age cannot be greater than maximum age."
- [ ] The system should also validate that the provided age range is between 0 and 150 years old.
  - [ ] Error: "Invalid age range. Age must be between 0 and 150."
- [ ] If no users fall within the specified age range, the system should return an empty JSON array.

#### Technical Requirements
- [ ] Implement a method in the “UserService” that leverages the “UserRepository” to find users by age.
- [ ] Validation logic for the age range should be encapsulated within the service method, throwing ServiceExceptions as necessary for invalid inputs.
- [ ] Add endpoint `/users/search/age/{min}/{max}` in “UserController”
- [ ] Create a ServiceException class that extends RuntimeException in the service package.
- [ ] Test all the (un)happy paths in the service. No error handling is required in the controller yet.
- [ ] The endpoint is tested with **HTTP Client**.