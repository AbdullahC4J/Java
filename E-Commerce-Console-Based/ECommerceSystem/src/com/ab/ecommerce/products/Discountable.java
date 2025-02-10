package com.ab.ecommerce.products;

public interface Discountable {
    /**
     * Calculates the discount amount for the product
     * @return The discount amount
     */
    double calculateDiscount();
    
    /**
     * Gets the final price after applying the discount
     * @return The final price after discount
     */
    double getFinalPrice();
}