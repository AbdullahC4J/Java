# E-Commerce-Console-Based

A Java-based e-commerce system that provides functionality for managing products, users, shopping carts, and orders.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Class Documentation](#class-documentation)
- [Contributing](#contributing)

## Features

### Product Management
- Multiple product categories:
  - Books
  - Electronics
  - Fashion
  - Supermarket items
- Product attributes including name, price, and category-specific details
- Automatic discount calculations based on product type

### User Management
- Two types of users:
  - Administrators
  - Customers
- Secure authentication system
- Role-based access control

### Shopping Features
- Shopping cart functionality
- Order processing
- Bill generation
- Discount calculations

### Admin Features
- Product inventory management
- Stock level monitoring
- User management
- Sales reporting

## Project Structure

```plaintext
src/com/ab/ecommerce/
├── products/
│   ├── Book.java
│   ├── Electronic.java
│   ├── Fashion.java
│   ├── SuperMarket.java
│   ├── Product.java (abstract)
│   ├── Discountable.java (interface)
│   └── PrintProdInfo.java (interface)
├── users/
│   ├── Admin.java
│   ├── Customer.java
│   └── User.java (abstract)
├── cart/
│   └── Cart.java
├── stock/
│   └── Stock.java
├── billsandpayment/
│   └── Bill.java
├── Store.java
├── UserInterface.java
└── Main.java
```

## Usage

### Admin Operations
1. Sign up/Login as admin
2. Manage inventory:
   - Add new products
   - Remove products
   - View stock levels
3. View product categories

![alt text](https://github.com/AbdullahC4J/Java/blob/main/E-Commerce-Console-Based/Images/ECC.png) ![alt text](https://github.com/AbdullahC4J/Java/blob/main/E-Commerce-Console-Based/Images/ECC(2).png)

### Customer Operations
1. Sign up/Login as customer
2. Browse products
3. Add items to cart
4. Remove items from cart
5. View cart
6. Checkout

![alt text](https://github.com/AbdullahC4J/Java/blob/main/E-Commerce-Console-Based/Images/ECC%20(4).png) ![alt text](https://github.com/AbdullahC4J/Java/blob/main/E-Commerce-Console-Based/Images/ECC%20(6).png)

## Class Documentation

### UML Diagram
![alt text](https://github.com/AbdullahC4J/Java/blob/main/E-Commerce-Console-Based/UML/UML.png)

### Product Classes
- `Product`: Abstract base class for all products
- `Book`: Represents book products with author information
- `Electronic`: Represents electronic products with brand and type
- `Fashion`: Represents fashion items with size and color
- `SuperMarket`: Represents supermarket items with categories

### User Classes
- `User`: Abstract base class for all users
- `Admin`: Administrator user with inventory management capabilities
- `Customer`: Customer user with shopping capabilities

### Shopping Classes
- `Cart`: Manages shopping cart operations
- `Bill`: Handles bill generation and payment processing
- `Stock`: Manages product inventory

## Security Features
- Password validation
- Role-based access control
- Session management
- Input validation

## Discount System
Different discount rates apply based on product category:
- Books: 15% discount
- Electronics: 10-20% based on type
- Fashion: 30% discount
- Supermarket: 10-40% based on type

## Authors
- AbdullahC4J

<!-- ## Future Improvements
- [ ] Implement payment gateway integration
- [ ] Add email notification system
- [ ] Implement product search functionality
- [ ] Add product reviews and ratings
- [ ] Implement order tracking
- [ ] Add inventory alerts
- [ ] Implement user profile management
- [ ] Add reporting and analytics -->
