package com.ab.ecommerce.products;

/**
 * Represents fashion products in the e-commerce system.
 * Handles clothing and accessories with specific attributes like type, color, and size.
 * All fashion items receive a standard 30% discount.
 */
public class Fashion extends Product {  
    /** The type of fashion item (e.g., Shirt, Pants, Dress, etc.) */
    private String fashionType;
    
    /** The color of the fashion item */
    private String color;
    
    /** The size of the fashion item */
    private String size;

    /**
     * Constructs a new Fashion product with the specified attributes.
     * 
     * @param name        The name of the product
     * @param price       The price of the product
     * @param fashionType The type of fashion item
     * @param color       The color of the fashion item
     * @param size        The size of the fashion item
     */
    public Fashion(String name, double price, String fashionType, String color, String size) {
        super(name, price,"Fashion");
        setFashionType(fashionType);
        setColor(color);
        setSize(size);
    }

    /**
     * Gets the type of fashion item.
     * @return The fashion item type
     */
    public String getFashionType() {
        return fashionType;
    }

    /**
     * Gets the color of the fashion item.
     * @return The color of the item
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the size of the fashion item.
     * @return The size of the item
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the fashion type.
     * @param fashionType The type of fashion item
     */
    public void setFashionType(String fashionType) {
        if (fashionType == null || fashionType.trim().isEmpty()) {
            throw new IllegalArgumentException("Fashion type cannot be empty");
        }
        if (!fashionType.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Fashion type can only contain letters, spaces, and hyphens");
        }

        this.fashionType = fashionType;
    }

    /**
     * Sets the color of the fashion item.
     * @param color The color of the fashion item
     */
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        if (!color.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Color can only contain letters, spaces, and hyphens");
        }

        this.color = color;
    }

    /**
     * Sets the size of the fashion item.
     * @param size The size of the fashion item
     */
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty");
        }
        if (!size.matches("^[XSMLxsml0-9]+$")) {
            throw new IllegalArgumentException("Invalid size format (e.g., XS, S, M, L, XL, XXL, or numeric)");
        }

        this.size = size;
    }


    /**
     * Prints detailed information about the fashion item
     * including name, type, color, and size.
     */
    @Override
    public void printProductInfo() {
        System.out.println("Fashion Details:"); 
        System.out.println("Name: " + getName());
        System.out.println("Fashion Type: " + fashionType);
        System.out.println("Color: " + color);
        System.out.println("Size: " + size);
        System.out.println("Price: $" + getPrice());
    }       

    /**
     * Calculates the discount for fashion items.
     * All fashion items receive a standard 30% discount.
     * 
     * @return The calculated discount amount
     */
    @Override
    public double calculateDiscount() {
        return getPrice() * 0.3;  // 30% discount for all fashion items
    }
}
