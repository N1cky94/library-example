# First Feature Wave

> This repository is created to aid students
> in a simple Java project that will travel through
> Java and Spring into a REST API application

## Content

These are the stories and features that make version **V0.1.N**.
We are now introducing the web-API

1. [Spring Boot](#framework-upgrade-implement-spring-boot-and-web)
2. [Retrieve all users over web-API](#story-6-retrieve-all-users)


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
- [ ] The endpoint returns a list of all users registered in the system with their details.
- [ ] Each user's information includes name, age, email, password.
- [ ] If no users are registered, the API returns an empty list.
- [ ] In case of a server error, an appropriate error message is returned.

#### Technical Requirements:
- [ ] Add a new endpoint in UserRestController to handle the GET request.
- [ ] Test all the (un)happy paths in the service. No error handling is required in the controller yet.
- [ ] The endpoint is tested with Thunderclient.