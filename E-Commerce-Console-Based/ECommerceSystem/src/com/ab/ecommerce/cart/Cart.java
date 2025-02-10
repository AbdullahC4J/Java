package com.ab.ecommerce.cart;

import com.ab.ecommerce.products.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart in the e-commerce system.
 * Manages products, calculates totals, and handles discounts.
 * Each cart has a maximum capacity and maintains both original
 * and discounted prices for all items.
 * Note: The cart operated through the customer only.
 */
public class Cart {
    /** List to store products added to cart */
    private List<Product> productsInCart;
    
    /** Total price before any discounts */
    private double totalPrice;

    /** Total price after applying all product discounts */
    private double totalAfterDiscount;

    /** Maximum number of items allowed in a single cart */
    private final int MAX_ITEMS = 30;

    /**
     * Constructs an empty cart with initialized totals.
     */
    public Cart() {
        this.productsInCart = new ArrayList<>();
        this.totalPrice = 0.0;
        this.totalAfterDiscount = 0.0;
    }

    /**
     * Gets the total price before discounts.
     * @return The original total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the final total after applying all discounts.
     * @return The discounted total price
     */
    public double getTotalAfterDiscount() {
        return totalAfterDiscount;
    }

    /**
     * Adds a product to the cart and updates totals.
     * @param product The product to add
     * @throws IllegalArgumentException if product is null
     * @throws IllegalStateException if cart is full
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (productsInCart.size() >= MAX_ITEMS) {
            throw new IllegalStateException("Cart is full (max " + MAX_ITEMS + " items)");
        }
        productsInCart.add(product);
        totalPrice += product.getPrice();
        totalAfterDiscount += product.getFinalPrice();
    }

    /**
     * Removes a product from the cart and updates totals.
     * @param product The product to remove
     */
    public void removeProduct(Product product) {
        if (productsInCart.remove(product)) {
            totalPrice -= product.getPrice();
            totalAfterDiscount -= product.getFinalPrice();
        }
    }

    /**
     * Gets the current number of items in cart.
     * @return Number of items
     */
    public int getItemCount() {
        return productsInCart.size();
    }

    /**
     * Checks if the cart has any items.
     * @return true if cart is empty, false otherwise
     */
    public boolean isEmpty() {
        return productsInCart.isEmpty();
    }

    /**
     * Removes all items and resets totals.
     */
    public void clear() {
        productsInCart.clear();
        totalPrice = 0.0;
        totalAfterDiscount = 0.0;
    }

    /**
     * Displays cart contents and total.
     * Shows each product with its original price.
     */
    public void printCart() {
        if (isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        System.out.println("\nCart Contents:");
        System.out.println("-------------");
        for (Product product : productsInCart) {
            System.out.printf("%s - $%.2f%n", 
                product.getName(), 
                product.getPrice());
        }
        System.out.println("-------------");
        System.out.printf("Total: $%.2f%n", totalPrice);
    }
}