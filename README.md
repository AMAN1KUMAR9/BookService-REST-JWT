# Book Management REST API

## Overview

This is a **Spring Boot** project that provides a **REST API** for managing books with **JWT-based authentication**. The API allows users to perform CRUD operations on books, apply filters, and authenticate using JWT tokens.

## Features

- **User Authentication** (JWT-based login & authorization)
- **CRUD Operations** (Create, Read, Update, Delete Books)
- **Filtering & Searching** (Search by title, author, category, rating)
- **Spring Security Integration** (Secure API endpoints)
- **Pagination Support**

## Tech Stack

- **Java** (Spring Boot, Spring Security, Spring Data JPA)
- **JWT** (JSON Web Token for authentication)
- **H2 Database** (In-memory database for testing)
- **Swagger** (API Documentation)
- **Maven** (Dependency Management)

## Setup & Installation

1. **Clone the repository:**

   ```sh
   git clone https://github.com/yourusername/repository.git
   cd repository
   ```

2. **Configure the Database:**

   - The default configuration uses **H2 Database** (Check `application.properties`).
   - If using **MySQL**, update the database settings accordingly.

3. **Build & Run the Project:**

   ```sh
   mvn spring-boot:run
   ```

4. **Access the API:**

   - Swagger UI: [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)
   - H2 Console: [`http://localhost:8080/h2-console`](http://localhost:8080/h2-console) (if enabled)

## API Endpoints

### Authentication

- **Login:** `POST /users/login` (Returns JWT token)
- **Register:** `POST /users/register`

### Book Management

- **Get All Books:** `GET /books`
- **Get Book by ID:** `GET /books/{id}`
- **Add a New Book:** `POST /books`
- **Update Book:** `PUT /books/{id}`
- **Delete Book:** `DELETE /books/{id}`

### Filters & Search

- **Search by Title:** `GET /books/search?title=xyz`
- **Filter by Author:** `GET /books/filter?author=xyz`
- **Filter by Category:** `GET /books/filter?category=xyz`
- **Filter by Rating:** `GET /books/filter?rating=4`

## Security & Authentication

- **JWT Authentication**: Users must log in to receive a token.
- **Secure Endpoints**: API requests require a `Bearer` token in the Authorization header.
- **Spring Security Integration** ensures restricted access based on user roles.

## Contributing

1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Commit your changes (`git commit -m "Added new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

##

---

### Author: Aman Kumar

