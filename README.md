# **Book Management Application**

## Overview

This project is a Book Management Application that allows users to manage categories, books, and borrow requests. It consists of four modules: Auth, Category, Book, and BorrowRequest.

### Auth Module
Responsible for user authentication. It includes APIs for user registration and authentication. The Postman collection for these APIs can be found in the Postman Collection section.

### Category Module
Manages categories for books. It provides CRUD operations for managing book categories.

### Book Module
Handles books in the system. It offers CRUD operations for managing books, including details such as title, author, quantity, etc.

### BorrowRequest Module
Manages borrow requests for books. Users can create, view, update, and delete borrow requests. Additionally, there are APIs for approving and rejecting borrow requests.

## How to Use

### Auth Module

#### Register User:

- Use the provided Register API in the Postman Collection.
- Register a user as a normal user or an admin.
- Retrieve the authentication token from the response.
- Please note that the Token may expire in a configured time , if so you will need to rerun the authenticate API for the required user and put the returned token value to the Authorization Header of type Bearer type in psostman
#### Authentication:

- Use the obtained token in the Authorization header (Bearer Token) for subsequent requests.

### Category Module

- Use the Category APIs in the Postman Collection for CRUD operations on book categories.

### Book Module

- Utilize the Book APIs in the Postman Collection for CRUD operations on books.

  **Note: Users can only view books.**

### BorrowRequest Module

- Leverage the BorrowRequest APIs in the Postman Collection for CRUD operations on borrow requests.

- **Admin** can approve and reject requests on book that are made by users.
  
- **User Permissions:**
    - Users can create a borrow request.
    - Users can view their borrow requests.
    - Users can delete their own borrow requests.

## Installation and Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/SherifElNayad/book-managment
# Database Setup

## Create Database Schema:

1. Open your preferred MySQL database management tool (e.g., MySQL Workbench).

2. Execute the following SQL script to create the `book-management` schema:

   ```sql
   CREATE SCHEMA IF NOT EXISTS `book-management` DEFAULT CHARACTER SET utf8mb4;
3. Update the database connection properties according to your local MySQL setup:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/book-management
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password