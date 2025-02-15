package com.ab.ecommerce.products;

/**
 * Represents a Book product in the e-commerce system.
 * Extends the Product class and includes book-specific attributes like author.
 */
public class Book extends Product {

    /** The author of the book */
    private String author;

    /**
     * Constructs a new Book with the specified attributes.
     * 
     * @param name     The title of the book
     * @param price    The price of the book
     * @param author   The author of the book
     */
    public Book(String name, double price, String author) {
        super(name, price, "Book");
        setAuthor(author);
    }


    /**
     * Gets the book's author.
     * @return The author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author The author of the book
     */
    public void setAuthor(String author) {
        
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        if (author.length() < 2) {
            throw new IllegalArgumentException("Author name must be at least 2 characters long");
        }   
        if (!author.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Author can only contain letters, spaces, and hyphens");
        }
        
        this.author = author;
    }

    /**
     * Prints detailed information about the book including
     * title, author, price.
     */
    @Override
    public void printProductInfo() {
        System.out.println("--------------------------------");
        System.out.println("Title: " + getName());
        System.out.println("Author: " + author);
        System.out.println("Price: $" + getPrice());
        System.out.println("--------------------------------\n");
    }

    /**
     * Calculates the discount amount for the book.
     * Books receive a 15% discount if eligible.
     * 
     * @return The calculated discount amount
     */
    @Override
    public double calculateDiscount() {
            return getPrice() * 0.15; // 15% discount for books
    }
}