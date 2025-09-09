# Quiz Application Using Microservices

A robust microservices-based Quiz Application built with Spring Boot, Spring Cloud, and Eureka for service discovery. This project showcases a fluid architecture with decoupled microservices, leveraging an API Gateway and Feign Client for streamlined inter-service communication, and persistent storage with MySQL.

---

##  Project Components

### 1. **Question Service**
- Manages question bank CRUD operations.
- Exposes endpoints such as:
  - `GET /question/getAll`
  - `GET /question/getBy/{category}`
  - `POST /question/generate/{category}/{numberOfQuestions}`
  - `POST /question/getScore`
  - `POST /question/getByQuestionId`

### 2. **Quiz Service**
- Coordinates quiz creation and scoring logic.
- Uses Feign to communicate with the Question Service.
- Exposes endpoints like:
  - `POST /quiz/create?category=...&numQ=...&title=...`
  - `GET /quiz/get/{quizId}`
  - `POST /quiz/submit` (submits answers for scoring)

### 3. **API Gateway**
- Built using Spring Cloud Gateway with Eureka Discovery.
- Routes external requests to Quiz and Question services.
- Configured using the latest `spring.cloud.gateway.server.webflux.*` properties for Spring Cloud 2025 compatibility.

---

##  Key Features

- **Service Discovery** via Eureka — dynamic registration and look-up of microservices.
- **Declarative HTTP Client** using Spring Cloud OpenFeign for simplified inter-service REST calls.
- **API Gateway** for unified routing and entry point to microservices (“/quiz/**” and “/question/**”).
- **Configurable via `application.properties`**, compatible with Spring Boot 3.5.x and Spring Cloud 2025.0.0.
- **MySQL-based persistence** for quiz metadata and question IDs.
- **Reactive-aware Gateway configuration** using `spring-cloud-starter-gateway-server-webflux`.

---

##  Quick Start

1. **Start Eureka Server**
2. **Run Question-Service**
3. **Run Quiz-Service**
4. **Run API-Gateway**
5. Test via Postman:
   - `POST http://localhost:<gateway-port>/quiz/create?category=java&numQ=5&title=SampleQuiz`
   - `GET  http://localhost:<gateway-port>/quiz/get/{id}`
   - `POST http://localhost:<gateway-port>/quiz/submit` with JSON body of `UserAnswerDTO` list.

---

##  Why This Project?

- Demonstrates a clean, modular architecture using Spring Boot microservices.
- Illustrates modern Spring Cloud practices and patterns (API Gateway, Eureka, Feign).
- Fully reactive routing via WebFlux-compatible gateway configuration.
- Easily extensible for features like security, monitoring, or additional microservices.

---

##  Next Steps

- Add global exception handlers and validation for robust service behavior.
- Implement security (e.g., JWT, OAuth2) via Gateway integration.
- Enhance monitoring and tracing (Actuator, Sleuth + Zipkin).
- Write unit and integration tests for business and controller logic.

---
