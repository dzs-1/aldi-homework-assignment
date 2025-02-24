## Dózsa Péter - ALDI Homework Assignment

This repository contains solutions for a Quality Assurance Engineer position assignment. 

The tasks involve Manual Testing, Frontend Testing, API Testing, and bonus questions on Docker, JUnit + Selenium, and CI Integration.

All the tasks and questions are in their separate folder/package.

The tasks are not testing an actual application or service, except for the manual testing case.
However, the code structure is a able to run the tests.

### Prerequisites
- Java 19+ 
- IntelliJ IDEA - project setup and execution
- Maven - managing dependencies
- Docker - containerized testing environment

### Setup
- Install Java 19+ and Maven
- Import the project into IntelliJ IDEA
- Run `mvn clean install` to install dependencies

### Test Execution
#### IntelliJ
- Open the test class in IntelliJ
- Right-click and select Run <TestName>

#### Maven
- If you want to run all the test in the package, use `mvn test` 
- You might need to pass environment variables for the execution, in that case use
  `mvn test -Denv=<env>`