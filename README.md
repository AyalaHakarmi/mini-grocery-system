# Grocery Management System 🍅  
_Java-based Spring Boot Web Application with JSF UI (JoinFaces)_

## 📋 Overview

This is a full-stack web application designed to help a local grocery store manage inventory and order products from suppliers. It includes both server-side and client-side interfaces. The project implements all required features, **including the bonus task**.

> ⚠️ **Note:** This project was built in a very short timeframe — so if you find some missing edge case… _go easy on me_ 😅

---

## 🎥 Demo Video (Highly Recommended!)

👉 I recorded a short video showing exactly how to run and use the system, and I showed how all the data is updated in the database.
📂 The video file is included in the project under:  
📺 Watch it here: [View on Google Drive](https://drive.google.com/file/d/1FDLk2Yeonlocn3JhhlwxBETagESlaadD/view?usp=sharing)

> 🔴 Make sure to watch it before running the project!

---

## 🛠 Technologies Used

- **Backend:** Java 21 + Spring Boot (Maven Project)  
- **Database:** PostgreSQL  
- **Persistence:** JPA with EclipseLink  
- **UI Framework:** JSF (Jakarta Server Faces) with JoinFaces integration  
- **Deployment:** Spring Boot standalone application  
- **IDE:** NetBeans 25  

---

## 🔐 Owner Login

- **Password:** `grocery123`  
  (Defined as a `final` field in the `OwnerLoginBean` class — can be easily changed)

---

## ✨ Features Implemented

### Grocery Owner:

- View all orders  
- View existing orders and update status to “Completed”  
- Define minimum stock quantity per product  
- Order products from suppliers  

### Suppliers:

- Register and add products for sale  
- View all orders from the grocery owner and confirm them  
  _(status changes from `Pending` to `In-Process`)_

---

## 🏆 Bonus Task: Inventory Management + Auto Ordering ✅

✅ Fully implemented!

- Each product stores:
  - `min_stock_quantity` – minimum desired stock defined by the grocery owner  
  - `current_quantity` – the product's current quantity in stock  
- These values are automatically updated when an order is marked as completed  
- Basic support for automatic ordering based on stock levels (extensible in the future)

---

<sub>

## 📦 Running the Project

### Prerequisites

- Java JDK 21+  
- PostgreSQL installed and running  
- NetBeans 25 or any IDE that supports Maven  

### Database Configuration

Update `application.properties`:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/grocerydb

spring.datasource.username=your_db_username

spring.datasource.password=your_db_password

Ensure the database `grocerydb` exists before running.

### Running

Run `MainApplication.java` from your IDE or execute:

```bash
mvn spring-boot:run
```

Access the application at: [http://localhost:8080](http://localhost:8080)

</sub>

---

<sub><sub>🛈 Note: the supplier-side application was accidentally deployed with a custom context path (`/supplier-client`). The server-side application does not define an explicit context path and runs at root (`/`).</sub></sub>


