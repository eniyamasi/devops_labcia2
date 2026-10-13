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

## Jenkins Declarative Pipeline

The root-level `Jenkinsfile` defines a CI pipeline for a Windows Jenkins agent using `bat` steps. All Maven commands execute from the repository root.

**Pipeline stages**
1. Checkout the source code.
2. Run `mvn clean test`.
3. Run `mvn package` only after the test stage succeeds.
4. Publish JUnit reports from `course-registration-app/target/surefire-reports/TEST-*.xml`.
5. Archive `course-registration-app/target/*.jar` only when the pipeline succeeds.

**Execution status:** Jenkins is not installed or available in the current environment. The Jenkins pipeline has been defined but has not been executed in Jenkins. Maven build, test and package commands are verified separately in the local environment.