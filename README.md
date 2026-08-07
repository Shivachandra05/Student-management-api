# Student Management REST API

A secure backend application built with **Java and Spring Boot** for managing student records through RESTful APIs.

This project demonstrates backend development concepts including layered architecture, MySQL database persistence, DTO validation, centralized exception handling, pagination and sorting, JWT-based authentication, and role-based authorization.

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- MySQL
- Maven
- Jakarta Validation
- AWS EC2 (deployment)

## Features

- RESTful CRUD operations for student management
- Layered architecture using Controller, Service, and Repository layers
- DTO-based request and response handling
- Input validation using Jakarta Validation
- Centralized exception handling
- Pagination and sorting
- JWT-based authentication
- Spring Security integration
- Role-based authorization for USER and ADMIN roles
- BCrypt password hashing
- MySQL persistence using JPA/Hibernate
- Environment variables for database credentials and JWT secrets
- Public health-check endpoint

## Architecture

The backend follows a layered architecture:

```text
Client / React Frontend
        |
        v
StudentController
        |
        v
StudentService
        |
        v
StudentRepository
        |
        v
JPA / Hibernate
        |
        v
MySQL
```

## JWT Authentication Flow

```text
User sends username + password
        |
        v
AuthController
        |
        v
AuthenticationManager
        |
        v
Credentials verified
        |
        v
JwtService generates JWT
        |
        v
Client receives token
        |
        v
Authorization: Bearer <token>
        |
        v
JwtAuthFilter validates token
        |
        v
Spring Security authenticates request
        |
        v
Protected Student API
```

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students/auth/register` | Register a new user |
| POST | `/students/auth/login` | Authenticate user and receive a JWT |

### Student Management

| Method | Endpoint | Description |
|---|---|---|
| POST | `/students/all` | Create a new student |
| GET | `/students/all` | Get all students |
| GET | `/students/{id}` | Get a student by ID |
| PUT | `/students/{id}` | Update a student |
| DELETE | `/students/{id}` | Delete a student (ADMIN only) |
| GET | `/students` | Get students with pagination and sorting |

### Health Check

```text
GET /public/health
```

Example response:

```json
{
  "status": "ok"
}
```

## Pagination and Sorting

Example request:

```text
GET /students?page=0&size=3&sortBy=id&direction=asc
```

Supported sorting fields:

- `id`
- `name`
- `course`

Supported directions:

- `asc`
- `desc`

## Project Status

- Backend REST API completed
- JWT authentication and role-based authorization implemented
- Previously deployed on AWS EC2 with a cloud-hosted MySQL database
- React frontend integration in progress

## Next Steps

- Connect React frontend with the backend API
- Add automated tests
- Containerize with Docker and Docker Compose
- Explore microservices architecture
