# Sample API Voting Manager Java

Spring Boot REST API responsible for managing voting sessions.

# Objective

Opens voting sessions, receives votes from associates, counts and makes the result available.

# Requirements

The sample-api-voting-manager-java application runs in Java Runtime Environment JRE 8 or Java 17 JDK.

# Functional Requirements

In cooperativism, each associate has one vote and decisions are taken in assemblies, by vote. Sample APIVoting Manager is a backend solution to manage these voting sessions.

This solution runs on the Amazon AWS cloud and promotes the following features through a REST API:

- Register a new schedule.
- Open a voting session on an schedule (the voting session must be open for a time specified in the opening call or 1 minute by default).
- Receive member votes on schedules (votes are 'Yes'/'No' only. Each member is identified by a unique id and can vote only once per schedule).
- Counting the votes and giving the result of the vote on the schedule.
- An integration with an external system that checks, based on the member's CPF, if he can vote:

	GET https://user-info.herokuapp.com/users/{cpf}
	If the CPF is invalid, the API will return HTTP Status 404 (Not found).
	If the CPF is valid, the API will return if the user can (ABLE_TO_VOTE) or cannot (UNABLE_TO_VOTE) perform the operation

# Non-Functional Requirements

- The security of the interfaces has been abstracted and any calls to the interfaces can be considered as authorized.
- Schedules and votes are persisted in an H2 file database and are not lost upon application restart.
- Integration with an external system that checks, based on the member's CPF, if a member can vote using Feign Client. Feign was created precisely with the aim of reducing the complexity to consume services. It is a project that was inspired by Retrofit, JAXRS-2.0 and WebSocket. It uses pluggable annotations that can be Feign annotations, JAX-RS, among others and has been incorporated by Spring into its cloud stack.
- The voting result needs to be communicated to the rest of the platform, this is done through messaging using Kafka Template. When the session poll close, post a message with the poll result. We chose to use Kafka because its architecture was designed for high performance and high availability and it is a cloud native solution.

We chose to use Spring Boot because it offers most of the resources ready for use, shortening the code length and following the best design practices with already optimized configurations. Thus, reducing development time and creating the entire application structure with less or almost no configuration.

# Versioning

API versioning allows you to iterate quickly when spotting bugs or contract changes to your API.
When we talk about API versioning, there are three possible ways to create a proper version of an API:

- URL, also known as version of path (URI). Example: /v2/products/product-name)
- Version control through a custom header. Example: Accept-Version: v2.
- Parameter by Query string. Example /products/product-name?version=v2.

URL or by path (URI) is the model we use by default in this project, as it is much clearer and simpler for the user to know which API he is accessing and extremely easy to implement.

# Tecnologies

- Java 17
- Spring Boot 2.6.9
- API REST	
- Maven
- Swagger 2
- H2 Database
- JUnit 5
- Mockito
- K6 performance test
- Feign Client
- Lombok
- AWS

# Run Settings in Local Environment

- It uses the configurations of local properties present in the application.properties file.
- Access to the H2 console through the link http://localhost:8080/h2-console using the user and password data provided in the application.properties file (the data is kept even after restart).
- Access to the Swagger API through the link http://localhost:8080/swagger-ui.html
 
 
 