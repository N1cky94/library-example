# Library Exercise

> 
> This project is a completed version of the Library exercise for college students in
> applied informatics.
> 
> The author of the code doesn't claim this code is perfect, nor is it the only solution.
> Just A interpretation of the documented requirements.
>

![App Version v0.1.6](https://img.shields.io/badge/Version-V0.1.6-yellow?style=plastic)
![App Status](https://img.shields.io/badge/Status-PREVIEW-orange?style=plastic)

## Content

1. [Setting up the project](#set-up)

## Set-up

### Java

This project runs with Java SE 21.
We recommend installing the OpenJDK version.
On Mac and Linux you can do this with SDKman.
On Windows you can do this with chocolately.

```shell
sdk install java 21.0.2-open
sdk default java 21.0.2-open
```

### Maven

Maven can be installed on the local machine, but we are using the Maven wrapper for this
project. This means you can use it without a locally installed version of Maven.

To compile the project and provide a jar, just run the following command.

```shell
./mvnw clean package
```

### GitHub

We are using a public repository on GitHub to provide the source code to learners.
This source code has a workflow (GitHub Actions) configured to run all tests after
a push or with a **Pull Request** (Merge request on other platforms).

## Running the Application

### Tests
While making changes, run ALL tests often.

To Test the web-API, we are using Postman and the webclient.
These files are kept in [test > resources > requests](src/test/resources/requests)

## Features

All features are described in our documentation folder under features: [docs > features](/docs/features).

The first wave of features is the main API of the application and uses only JUnit and Java.
You can find the description of the [features/stories in the documentation](/docs/features/wave1.md).