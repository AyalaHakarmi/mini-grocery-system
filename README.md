# Hadassim 5 – Final Assignment

This repository contains solutions for the final assignment from the Hadassim course. Each part of the assignment is organized into its own directory:

---

## ✅ Part A – Log Analysis (`part_A/part_a_logs_analysis`)
**Files:**
- `logs.txt` — the raw input data
- `split_logs.py` — separates logs into files by hour
- `count_errors.py` — counts error types across the split logs

### What We Did:
- Wrote a Python program that reads a large log file and splits it by hour into smaller files.
- Implemented efficient log parsing and writing using dictionaries and file streams.
- Counted the most common error messages (by error type) using a heap structure.

### Time Complexity:
To find the top N most frequent error types:
- We used a min-heap of size N to track the top errors.
- The total time complexity is: **O(U log N)**, where U is the number of unique error types.
- This is better than sorting all errors (which would be O(U log U)).

### Space Complexity:
- We maintain a counter (dictionary) with U keys (unique error types): **O(U)**.
- We use a heap of size N to store the top N frequent errors: **O(N)**.
- Therefore, the total space complexity is: **O(U + N)**.

---

## ✅ Part A – Time Series Processing (`part_A/part_b_time_series`)
**Files:**
- `base_processor.py` — base class for CSV and Parquet processors
- `csv_processor.py` — processor for `.csv` time series files
- `parquet_processor.py` — processor for `.parquet` time series files
- `main.py` — chooses processor based on user input
- `requirements.txt` — dependencies

### What We Did:
- Built a clean object-oriented architecture with inheritance.
- Automatically detects file format (CSV or Parquet).
- Based on the file size, chooses whether to split the time series by day or not.
- For each part (daily or whole), computes hourly average.
- Removes duplicates, missing values, negative values, and out-of-range values.

### Design Note:
We used inheritance to handle CSV and Parquet differently while sharing common logic. This separation makes the code easier to test and extend.

### Note 
The attached parquet file is not in the correct format. Please replace it with a file in the correct format.

### Real-Time Stream Processing (Question 3, Part B)
If the data arrives in a live stream rather than from a file, we must update the hourly averages incrementally, without having all the data in advance.

To handle this, we can maintain a running aggregation structure for each hour:

- Use a dictionary `hour → (sum, count)` to store the total value and number of values for each hour seen so far.
- For each incoming data point:
  1. Convert the timestamp to the relevant hour (e.g., floor to the hour).
  2. Update the `sum` and `count` for that hour.
  3. The average is computed as `sum / count`.

This approach allows constant-time updates and supports real-time streaming without needing to load historical data into memory.

**Note:** At the end of the stream (or periodically), we can output the computed averages per hour.

--- 

## ✅ Part B: Family Tree

### Step 1 - Creating the `people` Table:
- The user is prompted whether to use an existing `people.db` file or generate a new example.
- If no file is provided, we create a small mock dataset of people with fields: `Person_Id`, `Personal_Name`, `Family_Name`, `Gender`, `Father_Id`, `Mother_Id`, `Spouse_Id`.
- The table is saved to a **SQLite database**.
- **Note:** The generated dataset is only a small example for demonstration purposes.

### Step 2 - Completing Spouse Relations:
- Ensures that if person A lists person B as a spouse, then B also lists A.
- Handles asymmetric or missing spouse data.

### Step 3 - Building Family Tree:
- A new table `family_relations` is created.
- For each person, direct relationships (parents, children, siblings, spouses) are extracted and stored with a relation type.

### Output:
- Both tables (`people`, `family_relations`) are stored in the database file `people.db`.
- The user can choose to create a new table or use an existing one.
- An option is provided to display the content of each table in a clean format.

---

## ✅ Part C: 
- Answers to the  questions from Part C are provided in the file part_C/answers.md.

---
## ✅ Part D:


# Grocery Management System 🍅  
_Java-based Spring Boot Web Application with JSF UI (JoinFaces)_

## 📋 Overview

This is a full-stack web application designed to help a local grocery store manage inventory and order products from suppliers. It includes both server-side and client-side interfaces. The project implements all required features, **including the bonus task**.

> ⚠️ **Note:** This project was built in a very short timeframe — so if there you fing some missing edge case… _go easy on me_ 😅

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

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/grocerydb
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

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


