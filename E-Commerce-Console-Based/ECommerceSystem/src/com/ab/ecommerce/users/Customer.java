package com.ab.ecommerce.users;

import com.ab.ecommerce.billsandpayment.Bill;
import com.ab.ecommerce.cart.Cart;
import com.ab.ecommerce.products.Product;

import java.util.Scanner;

/**
 * Represents a customer in the e-commerce system.
 * Manages customer information, shopping cart, and checkout process.
 * Extends User class to inherit basic user functionality.
 */
public class Customer extends User{
    /** The customer's phone number */
    private String customerPhoneNumber;
    
    /** The customer's delivery address */
    private String customerAddress;
    
    /** The customer's shopping cart */
    private Cart customerCart;
    
    /** The customer's bill for current purchase */
    private Bill customerBill;

    /**
     * Constructs a new Customer with the specified details.
     * 
     * @param customerPhoneNumber The customer's phone number
     * @param customerAddress     The customer's delivery address
     * @param userName           The customer's username
     * @param password          The customer's password
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Customer(String customerPhoneNumber, String customerAddress, String userName, String password) {
        super(userName, password);
        setCustomerPhoneNumber(customerPhoneNumber);
        setCustomerAddress(customerAddress);
        this.customerCart = new Cart();
        this.customerBill = new Bill(customerCart);

    }

    /**
     * Gets the customer's phone number.
     * @return The phone number
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Gets the customer's delivery address.
     * @return The delivery address
     */
    public String getCustomerAddress() {    
        return customerAddress;
    }

    /**
     * Sets the customer's phone number.
     * @param customerPhoneNumber The new phone number
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        if(customerPhoneNumber == null || customerPhoneNumber.trim().isEmpty()){
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(customerPhoneNumber.length() != 11){
            throw new IllegalArgumentException("Phone number must be 11 digits");
        }
        if(!customerPhoneNumber.matches("\\d+")){
            throw new IllegalArgumentException("Phone number must contain only digits");
        }

        this.customerPhoneNumber = customerPhoneNumber;
    }




    /**
     * Sets the customer's delivery address.
     * @param customerAddress The new delivery address
     */
    public void setCustomerAddress(String customerAddress) {
        if(customerAddress == null || customerAddress.trim().isEmpty()){
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if(customerAddress.length() < 3){
            throw new IllegalArgumentException("Address must be at least 3 characters long");
        }
        
        this.customerAddress = customerAddress;
    }   

    /**
     * Displays customer's personal information.
     * Shows name, phone number, and address.
     */
    public void printCustomerInfo() {
        System.out.println("Customer Information:");
        System.out.println("Name: " + getUserName());
        System.out.println("Phone Number: " + customerPhoneNumber);
        System.out.println("Address: " + customerAddress);
    }

    /**
     * Adds a product to the customer's shopping cart.
     * @param product The product to add
     * @throws IllegalArgumentException if product is null
     */
    public void addProductToCart(Product product) {
        customerCart.addProduct(product);

    }

    /**
     * Removes a product from the customer's shopping cart.
     * @param product The product to remove
     */
    public void removeProductFromCart(Product product) {
        customerCart.removeProduct(product);
    }

    /**
     * Processes the checkout operation.
     * Checks cart contents, displays bill,
     * and completes the purchase.
     */
    public void checkout() {
        if(!getIsPasswordChecked()) {
            System.out.println("Enter" + getUserName() + "s password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            scanner.close();

            if (verifyPassword(password)) {
                setIsPasswordChecked(true);
            } else {
                System.out.println("Incorrect password");
                return;
            }
        }
        printCustomerInfo();

        if (customerCart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        System.out.println("Checking out...");
        customerCart.printCart();
        customerBill.printBill();
        System.out.println("Thank you for shopping with us!");
        customerCart.clear();
    }
}