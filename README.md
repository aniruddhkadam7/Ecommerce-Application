# MyFirstApp - E-commerce Application

## Overview
MyFirstApp is a Spring Boot-based eCommerce application that enables users to browse, purchase, and manage products securely. It utilizes modern Java technologies and integrates MySQL as the database for data persistence. The application follows best practices for security, templating, and database management.

## Features
- User authentication and authorization with Spring Security.
- Product catalog management.
- Shopping cart functionality.
- Order placement and history tracking.
- Secure transactions using Spring Security.
- Thymeleaf-based UI for a dynamic frontend.
- Supports both MySQL and H2 databases.

## Tech Stack
- **Backend:** Spring Boot, Spring Data JPA, Spring Security
- **Frontend:** Thymeleaf, HTML, CSS
- **Database:** MySQL, H2 (for development and testing)
- **Build Tool:** Maven
- **Java Version:** 17

## Prerequisites
To run this application, you need:
- Java 17 or later
- Maven installed
- MySQL database running

## Installation and Setup
1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/MyFirstApp.git
   cd MyFirstApp
   ```
2. **Configure Database**
   Update `application.properties` in `src/main/resources/` to configure your MySQL database:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```
3. **Build and Run**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the Application**
   Open a browser and go to: `http://localhost:8080`

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| GET | `/products` | Fetch all products |
| POST | `/cart/add/{id}` | Add product to cart |
| POST | `/checkout` | Process order |
| GET | `/orders` | View order history |

## Development & Contribution
1. Fork the repo and create a feature branch.
2. Make changes and commit with descriptive messages.
3. Push to your fork and create a Pull Request.

## License
This project is licensed under the MIT License.

---
Feel free to customize this README as per your project's additional features and requirements!

