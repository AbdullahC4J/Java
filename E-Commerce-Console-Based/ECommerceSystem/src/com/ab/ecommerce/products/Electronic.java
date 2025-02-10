package com.ab.ecommerce.products;

/**
 * Represents electronic products in the e-commerce system.
 * Handles electronic devices with specific attributes like brand, type, and color.
 */
public class Electronic extends Product {
    /** The brand of the electronic device */   
    private String brand;
    /** The type of electronic device */
    private ElectronicProductType productType;
    /** The color of the device */
    private String color;

    /**
     * Constructs a new Electronic product.
     * 
     * @param name        The name of the product
     * @param price       The price of the product
     * @param brand       The brand of the electronic device
     * @param productType The type of electronic device
     * @param color       The color of the device
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Electronic(String name, double price, String brand, ElectronicProductType productType, String color) {
        super(name, price, "Electronics");
        setBrand(brand);
        setProductType(productType);
        setColor(color);
    }

    /**
     * Gets the brand of the electronic device.
     * @return The brand name
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the color of the electronic device.
     * @return The color
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the brand of the electronic device.
     * @param brand The brand name to set
     */
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        if (!brand.matches("^[a-zA-Z0-9\\s-]+$")) {
            throw new IllegalArgumentException("Brand can only contain letters, numbers, spaces, and hyphens");
        }

        this.brand = brand;
    }

    /**
     * Sets the color of the electronic device.
     * @param color The color to set
     */
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be empty");
        }
        if (!color.matches("^[a-zA-Z\\s-]+$")) {
            throw new IllegalArgumentException("Color can only contain letters, spaces, and hyphens");
        }

        this.color = color;
    }

    /**
     * Gets the type of electronic device.
     * @return The product type (SMARTPHONE, LAPTOP, etc.)
     */
    public ElectronicProductType getProductType() {
        return productType;
    }

    /**
     * Sets the type of electronic device.
     * @param productType The product type to set
     */
    public void setProductType(ElectronicProductType productType) {
        if (productType == null) {
            throw new IllegalArgumentException("Product type cannot be null");
        }

        this.productType = productType;
    }

    /**
     * Prints detailed information about the electronic device
     * including name, brand, type, price, and color.
     */
    @Override
    public void printProductInfo() {
        System.out.println("Electronic Details:");
        System.out.println("Name: " + getName());
        System.out.println("Brand: " + brand);
        System.out.println("Product Type: " + productType);
        System.out.println("Price: $" + getPrice());
        System.out.println("Color: " + color);
        System.out.println("Final Price: $" + getFinalPrice());
    }

    /**
     * Calculates the discount based on the electronic type.
     * Different types have different discount rates:
     * - Smartphones, Laptops: 15% discount
     * - TVs, Cameras: 20% discount
     * - Other electronics: 10% discount
     * 
     * @return The calculated discount amount
     */
    @Override
    public double calculateDiscount() {
        return switch (productType) {
            case SMARTPHONE, LAPTOP -> getPrice() * 0.15;    // 15% discount
            case TV, CAMERA -> getPrice() * 0.20;           // 20% discount
            case TABLET, HEADPHONE, SPEAKER -> getPrice() * 0.10;  // 10% discount
            default -> 0.0;
        };
    }
}
