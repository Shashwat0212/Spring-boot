# Full Stack Spring-Boot & MYSQL - ShopMe - ECommerce Website

## Summary

* Developed a complete shopping website using Java, Spring Boot, Thymeleaf, Bootstrap, jQuery and MySQL database
* Implemented functionality to allow customers to browse products and place orders
* Created the Admin application for managing users, categories, brands, products, customers, orders.
* Added shopping cart features and payment via PayPal and credit cards. and deployed on Heroku with file upload on Amazon S3

## Features

* **Spring-Boot** backend with:
  * **Spring-Security**: Login Logout implementation with session handling
  * **Secure password** hashing by default
  * **token** authentication
* **HTML 5 + Thymeleaf**  frontend:
  * Login view
  * After login, main dashboard view
  * Frontend tests ran at build time (can be disabled too)
  * Made as modular as possible, so it works out of the box.
* **SQL Admin** for SQL database.
* **Two Applications** 
  * One backend Admin application to handle and manage the frontend application
  * Admin Application has control implementation for admin accounts based on their privilege defined at account creation
  * Frontend Application for customers to login to and add and view products.
* **Application Data Storage on Amazon S3 for seamless transfer.**
  * Used S3 to host application's admin and frontend related media


