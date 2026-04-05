# 🏨 Hotel Booking System (Full-Stack)

A production-ready **Hotel Booking System** built using **Spring Boot (Backend)** and **React.js (Frontend)**. This application provides a complete solution for hotel discovery, booking management, secure authentication, and payment processing.

---

## 🚀 Project Overview

The Hotel Booking System is a full-stack web application designed to simulate a real-world hotel reservation platform. It allows users to browse hotels, check availability, make bookings, and complete secure payments.



---

## ✨ Features

### 👤 User Features

* User Registration & Login (JWT Authentication)
* Browse Hotels & Rooms
* Search & Filter Hotels
* Book Rooms with Date Selection
* View Booking History
* Secure Online Payments (Stripe Integration)

### 🔐 Security Features

* JWT-based Authentication
* Role-Based Authorization (User/Admin)
* Protected API Endpoints
* Secure Password Handling

### 🛠️ Admin Features

* Add / Update / Delete Hotels
* Manage Room Availability
* View and Manage Bookings
* Dashboard Overview

---

## 🏗️ System Architecture

```
Client (React.js)
       ↓
REST API (Spring Boot)
       ↓
Database (MySQL)
```

* Frontend communicates via **Axios**
* Backend exposes **RESTful APIs**
* Database handles persistent storage

---

## 🛠️ Tech Stack

### 🔹 Backend

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA (Hibernate)
* MySQL
* JWT (JSON Web Token)
* Lombok
* Maven

### 🔹 Frontend

* React.js
* React Router DOM
* Axios
* Stripe Payment Integration
* Chart.js (if analytics included)
* Bootstrap / CSS

---

## 📂 Project Structure

```
hotel-booking-system/
│
├── backend/                 # Spring Boot Application
│   ├── src/main/java
│   ├── src/main/resources
│   ├── pom.xml
│
├── frontend/                # React Application
│   ├── src/
│   ├── public/
│   ├── package.json
```

---

## ⚙️ Installation & Setup

### 🔧 Backend Setup

1. Navigate to backend directory:

```bash
cd backend
```

2. Configure database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_db
spring.datasource.username=Easin
spring.datasource.password=root
```

3. Run the application:

```bash
mvn spring-boot:run
```

---

### 💻 Frontend Setup

1. Navigate to frontend directory:

```bash
cd frontend
```

2. Install dependencies:

```bash
npm install
```

3. Start the development server:

```bash
npm start
```

Frontend will run on:

```
http://localhost:3000
```

---

## 🔐 Authentication Flow

1. User logs in with credentials
2. Server validates and returns JWT token
3. Token is stored in client (localStorage)
4. Token is attached to every protected request
5. Backend validates token before processing

---

## 💳 Payment Integration

* Integrated with **Stripe API**
* Supports secure checkout flow
* Handles payment success and failure responses

---

## 📡 API Endpoints (Sample)

| Method | Endpoint           | Description         |
| ------ | ------------------ | ------------------- |
| POST   | /api/auth/register | Register new user   |
| POST   | /api/auth/login    | Authenticate user   |
| GET    | /api/hotels        | Get all hotels      |
| GET    | /api/rooms         | Get available rooms |
| POST   | /api/bookings      | Create booking      |

---

## 📊 Future Enhancements

* 🔍 Advanced Search & Filters
* 📱 Mobile Optimization
* 🧾 Invoice Generation (PDF)
* ⭐ Review & Rating System
* ☁️ Cloud Deployment (Docker + AWS)
* 🔔 Email & SMS Notifications



## 💡 Summary

This project demonstrates a **real-world full-stack hotel booking platform** with secure authentication, payment integration, and scalable architecture — making it suitable for professional portfolios and industry-level projects.

---
