# Library Management System

## Overview
The **Library Management System** is a Java-based application that allows users to register, log in, and perform various library operations such as adding books, viewing available books, issuing and returning books, and viewing overdue books. It uses **JDBC (Java Database Connectivity)** to interact with a MySQL database.

## Features
- **User Authentication**: Users can register and log in using email and password.
- **Role-based Access**: Admins can add books, while regular users can borrow and return books.
- **Book Management**:
  - Add new books.
  - View available books.
- **Transaction Management**:
  - Issue books to users.
  - Check borrowed books.
  - Return books.
  - View overdue books.

## Technologies Used
- **Java** (JDK 8+)
- **JDBC** (Java Database Connectivity)
- **MySQL** (Database for storing user and book records)

## Database Schema
Database Name- librarymanagementsystem
### Users Table
| Column    | Type                     | Description              |
|-----------|--------------------------|--------------------------|
| UserID    | INT (Auto_Increment)(PK) | Unique user identifier   |
| Name      | VARCHAR(50)              | User's full name         |
| Email     | VARCHAR(40)              | Unique email ID          |
| Password  | VARCHAR(25)              | User's password          |
| Role      | ENUM('Admin', 'User')    | Role: Admin/User         |
| Phone     | VARCHAR(10)              | User's phone number      |

### Books Table
| Column    | Type                          | Description              |
|-----------|-------------------------------|--------------------------|
| BookID    | INT (Auto_Increment)(PK)      | Unique book identifier   |
| Title     | VARCHAR(50)                   | Book title               |
| Author    | VARCHAR(50)                   | Book author              |
| ISBN      | VARCHAR(20)                   | ISBN number              |
| Status    | ENUM('Available', 'Borrowed') | Availability status      |

### Transactions Table
| Column        | Type                      | Description                   |
|---------------|---------------------------|-------------------------------|
| TransactionID | INT (Auto_Increment)(PK)  | Unique transaction identifier |
| UserID        | INT (FK)                  | Reference to Users table      |
| BookID        | INT (FK)                  | Reference to Books table      |
| IssueDate     | DATETIME                  | Date when book was issued     |
| ReturnDate    | DATETIME                  | Date when book was returned   |

## Setup & Installation
A.Use included LMS.sql file directly.

B.Use the bbelow commands.
### 1. Clone the Repository
```sh
git clone https://github.com/your-repo/library-management-system.git
cd library-management-system
```

### 2. Setup MySQL Database
- Create a database:
```sql
CREATE DATABASE libraymanagementsystem;
```
- Use the database:
```sql
USE librarymanagementsystem;
```
- Create the tables using the schema above.

### 3. Configure Database Connection
Modify the **LMS.java** file to set your database credentials:
```java
private static final String URL = "jdbc:mysql://localhost:3306/librarymanagementsystem";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

### 4. Compile and Run the Project
```sh
javac -cp ".;mysql-connector-java.jar" org/aniket/sql/Main.java
java -cp ".;mysql-connector-java.jar" org.aniket.sql.Main
```

## Usage Guide
1. **Run the program** and choose from the menu.
2. **Register** a new user or **log in** as an existing user.
3. **Admins can add books**; users can **view and borrow books**.
4. **To return a book**, enter the correct book ID.
5. **View overdue books** if applicable.

## Future Improvements
- Add password hashing for security.
- Implement a GUI using JavaFX or Swing.
- Improve error handling and logging.

## Author
- **Aniket Paul**
- https://github.com/Ghost4t7
- https://www.linkedin.com/in/aniket-paul-ghost4t7/

