package com.ab.ecommerce.users;

import com.ab.ecommerce.products.Product;
import com.ab.ecommerce.stock.Stock;

import java.util.Scanner;


/**
 * Represents an Administrator in the e-commerce system.
 * Admins have special privileges to manage products, stock, and system settings.
 */
public class Admin extends User{
    /** The unique identifier for the admin */
    private int adminId;
    /** The stock of the store */       
    private Stock stock;

    /**
     * Constructs an Admin with the specified credentials.
     * 
     * @param adminId       The unique identifier for the admin
     * @param adminName     The name of the admin
     * @param adminPassword The password of the admin
     * @param stock         The general stock of the store
     */
    public Admin(int adminId, String adminName, String adminPassword, Stock stock) {
        super(adminName, adminPassword);
        setAdminId(adminId);
        setStock(stock);
    }   

    /**
     * Gets the admins unique identifier.
     * @return The admin ID
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * Sets the admin's ID.
     * @param adminId The new admin ID
     * @throws IllegalArgumentException if the ID is invalid
     */
    public void setAdminId(int adminId) {
        if (adminId <= 0) {
            throw new IllegalArgumentException("Admin ID must be a positive number");
        }

        this.adminId = adminId;
    }

    /**
     * Sets the stock of the store.
     * @param stock The new stock of the store
     * @throws IllegalArgumentException if the stock is null
     */
    public void setStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be null");
        }

        this.stock = stock;
    }


    /**
     * Prints admin information including ID and name.
     * For security reasons, password is not displayed.
     */
    public void printAdminInfo() {
        System.out.println("Admin Information:");
        System.out.println("ID: " + adminId);
        System.out.println("Name: " + getUserName());
    }

    /**
     * Adds a product to the stock.
     * @param product The product to add
     */
    public void addProductToStock(Product product) {
         if(!getIsPasswordChecked()){
             System.out.println("Enter" + getUserName() + "'s password: ");
             Scanner scanner = new Scanner(System.in);
             String password = scanner.nextLine();
             scanner.close();
            
             if(verifyPassword(password)){
                setIsPasswordChecked(true);
             } else {
                System.out.println("Incorrect password");
                return;
            }
        }

        if(stock.addProduct(product)){
            System.out.println("The product has been added to the stock successfully");
        } else {
            System.out.println("The product is already in the stock");
        }
     }

    /**
     * Removes a product from the stock.
     * @param product to remove from stock
     */
    public void removeProductFromStock(Product product) {
        if(!getIsPasswordChecked()){
            System.out.println("Enter Admin password: ");
            Scanner scanner = new Scanner(System.in);
            String password = scanner.nextLine();
            scanner.close();
            
            if(verifyPassword(password)){
                setIsPasswordChecked(true);
            } else {
                System.out.println("Incorrect password");
                return;
            }
        }

        if(stock.removeProduct(product)){
            System.out.println("The product has been removed from the stock successfully");
        } else {
            System.out.println("The product is not in the stock");
        }
     }
}
