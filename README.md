# 🔑 JWT Authentication Microservice

A **Spring Boot microservice** that provides **user authentication and authorization** using **JWT (JSON Web Tokens)** and **MongoDB**.  
This project demonstrates secure login, registration, and role-based access control.

---

## ✨ Features
- **User Registration**
  - Register with `login`, `password`, and `role` (e.g., `ADMIN`, `USER`).
  - Passwords are hashed with **BCrypt** before saving in MongoDB.

- **User Login**
  - Authenticate using `login` and `password`.
  - On success, a **JWT token** is generated and returned to the client.

- **JWT Authentication**
  - All protected endpoints require a valid JWT in the `Authorization` header:
    ```
    Authorization: Bearer <your_token_here>
    ```
  - A custom Spring Security filter validates the token and sets authentication context.

- **Role-Based Access Control**
  - Example: only `ROLE_ADMIN` can access `/health`.
  - `/auth/register` is open to everyone.

- **Stateless Sessions**
  - The API is **stateless** (`SessionCreationPolicy.STATELESS`) — no server-side sessions.

- **Environment-Safe Secret Key**
  - JWT secret is read from an environment variable (`JWT_SECRET`) for better security.

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot** (Web, Security, Validation)
- **Spring Data MongoDB**
- **JWT (jjwt library)**
- **BCryptPasswordEncoder**
- **Postman / Android App** for testing

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>


API Endpoints:
✅ Register User
POST /auth/register
{
  "login": "john",
  "password": "123456",
  "role": "ADMIN"
}


✅ Login
POST /auth/login
{
  "login": "john",
  "password": "123456"
}

