package com.ab.ecommerce.products;

/**
 * Represents supermarket products in the e-commerce system.
 * Handles various product types like fruits, vegetables, meat, dairy, etc.
 * Each product type has its specific discount rate.
 */
public class SuperMarket extends Product {
    /** The specific type of supermarket product (e.g., Fruits, Vegetables, Meat, etc.) */
    private SuperMarketProductType smProductType; 

    /**
     * Constructs a new SuperMarket product with the specified attributes.
     * 
     * @param name          The name of the product
     * @param price         The price of the product
     * @param smProductType The specific type of supermarket product
     */
    public SuperMarket(String name, double price, SuperMarketProductType smProductType) {
        super(name, price,"SuperMarket");
        setSmProductType(smProductType);
    }

    /**
     * Gets the supermarket product type.
     * @return The type of supermarket product
     */
    public SuperMarketProductType getSmProductType() {
        return smProductType;
    }

    /**
     * Sets the supermarket product type.
     * @param smProductType The type of supermarket product to set
     */
    public void setSmProductType(SuperMarketProductType smProductType) {
        if (smProductType == null) {
            throw new IllegalArgumentException("Product type cannot be null");
        }

        this.smProductType = smProductType;
    }

    /**
     * Prints detailed information about the supermarket product
     * including name, price, and product type.
     */
    @Override   
    public void printProductInfo() {
        System.out.println("--------------------------------");
        System.out.println("Name: " + getName());
        System.out.println("Price: $" + getPrice());
        System.out.println("Product Type: " + smProductType);
        System.out.println("--------------------------------\n");
    }

    /**
     * Calculates the discount based on the supermarket product type.
     * Discount rates:
     * - Fruits, Vegetables: 10% discount
     * - Meat, Fish, Poultry: 20% discount
     * - Drinks, Bread: 30% discount
     * - Dairy, Eggs: 40% discount
     * 
     * @return The calculated discount amount
     */
    @Override
    public double calculateDiscount() {
        return switch (smProductType) {
            case FRUITS, VEGETABLES -> getPrice() * 0.1;     // 10% discount
            case MEAT, FISH, POULTRY -> getPrice() * 0.2;    // 20% discount
            case DRINKS, BREAD -> getPrice() * 0.3;          // 30% discount
            case DAIRY, EGGS -> getPrice() * 0.4;           // 40% discount
            default -> 0.0;
        };
    }
}