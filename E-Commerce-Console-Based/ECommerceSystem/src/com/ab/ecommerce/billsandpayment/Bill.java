package com.ab.ecommerce.billsandpayment;

import com.ab.ecommerce.cart.Cart;

/**
 * Represents a bill for a customer's purchase.
 * Handles bill generation, discount calculations, and bill display.
 * Works with Cart to provide final pricing and discount information.
 */
public class Bill {
    /** The cart associated with this bill */
    private Cart cart;

    /**
     * Creates a new bill from a shopping cart.
     * Calculates the discount percentage based on cart totals.
     * 
     * @param cart The cart containing the items to be billed
     */
    public Bill(Cart cart) {
        setCart(cart);
    }  
    
    /**
     * Sets the cart for the bill.
     * 
     * @param cart The cart to set
     */

    private void setCart(Cart cart){
        if(cart == null){
            throw new IllegalArgumentException("Cart is null");
        }
        this.cart = cart;
    }

    /**

     * Displays the complete bill details including:
     * - Number of items
     * - Original total
     * - Discounted total
     * - Applied discount percentage
     */ 
    public void printBill() {
        System.out.println("\nBill Details:");
        System.out.println("-------------");
        System.out.println("Number of Items: " + cart.getItemCount());
        System.out.printf("Original Total: $%.2f%n", cart.getTotalPrice());
        System.out.printf("Final Total: $%.2f%n", cart.getTotalAfterDiscount());
        System.out.printf("Discount Applied: %d%%%n", (int) (cart.getTotalAfterDiscount() / cart.getTotalPrice() * 100));
        System.out.println("-------------");
    }
} 