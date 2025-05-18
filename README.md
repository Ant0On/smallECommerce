# Small E-Commerce API

A minimal e-commerce backend application built with **Spring Boot**. This service provides endpoints for managing products, handling user registration and login, and securing endpoints with **JWT-based authentication**.

---

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security 6**
- **JWT (JJWT)**
- **PostgreSQL**
- **Maven**
- **Hibernate / JPA**
---

## Features

- User Registration and Login
- Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)
- JWT token generation and verification
- Secure product creation (only for authenticated users with `ADMIN` role)
- Public access to product listing and product details

---

##  Setup Instructions

### Prerequisites

- Java 17+
- Maven
- PostgreSQL (running on default port `5432`)
- Postman (optional, for testing)

###  Application Configuration

Update your `application.properties` (or `application.yml`) with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update