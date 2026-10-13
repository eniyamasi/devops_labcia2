# Student Course Registration System

## Overview
A Java-based course registration system demonstrating course capacity validation, prerequisite checking, and student registration management.

## Project Structure
- `pom.xml` — Parent Maven POM; manages shared configuration, JUnit dependency versions, and the child module.
- `course-registration-app/pom.xml` — Child Maven POM for the application JAR.
- `course-registration-app/src/main/java/` — Application source code.
- `course-registration-app/src/test/java/` — JUnit 5 test cases.

## Technology Stack
- Java 17
- Apache Maven
- JUnit 5

## Build and Test
Run these commands from the repository root:

```bash
mvn clean test
```

To build the child module and its required projects:

```bash
mvn -pl course-registration-app -am package
```

## Registration Rules
- Reject registration when a course has reached its capacity.
- Allow registration for the last available seat.
- Validate prerequisites before accepting registration.
- Accept CS3A1 as an alternative completed prerequisite when CS301 is required.
- Do not modify registration state when a registration request is rejected.

## Test Coverage
The test suite covers course capacity boundaries, prerequisite validation, alternative prerequisite completion, and state preservation after rejected registrations.