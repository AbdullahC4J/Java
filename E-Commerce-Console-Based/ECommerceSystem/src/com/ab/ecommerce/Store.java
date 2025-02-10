package com.ab.ecommerce;

import com.ab.ecommerce.stock.Stock;
import com.ab.ecommerce.users.Admin;
import com.ab.ecommerce.users.Customer;
import com.ab.ecommerce.products.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public final class Store {
    /** The stock of the store */   
    private final Stock stock = new Stock();
    /** The customers of the store */
    private final List<Customer> customers = new ArrayList<>(10);
    /** The admins of the store */
    private final List<Admin> admins = new ArrayList<>(3);

    /**
     * Constructs a new Store with the specified stock.
     */
    public Store(){
        admins.add(new Admin(1, "Abdullah", "Abdullah123", stock));
    }

    /**
     * Adds a new customer to the store.
     * @param customer The customer to add
     */
    public void addNewCustomer(Customer customer){
        customers.add(customer);
    }

    /**
     * Adds a new admin to the store.
     * @param adminName The admin name
     * @param adminPassword The admin password
     */
    public int addNewAdmin(String adminName, String adminPassword){
        Admin admin = new Admin(admins.getLast().getAdminId() + 1, adminName,  adminPassword, stock);
        admin.setIsPasswordChecked(true);
        admins.add(admin);
        return admin.getAdminId();
    }

    public int verifyAdminLogin(String name, String paswrd){
        for (Admin admin : admins) {

            if (name.equals(admin.getUserName())){
                if(admin.verifyPassword(paswrd)) {
                    admin.setIsPasswordChecked(true);
                    return admin.getAdminId();
                }
                else{
                    return -1;
                }
            }
        }
        return -1;
    }

    /**
     * Removes a customer from the store.
     * @param customer The customer to remove
     */
    public void removeCustomer(Customer customer){
        customers.remove(customer);
    }

    /**
     * Removes an admin from the store.
     * @param admin The admin to remove
     */
    public void removeAdmin(Admin admin){
        admins.remove(admin);
    }

    /**
     * Prints all customers in the store.
     */
    public void printCustomers(){
        for (Customer customer : customers) {
            customer.printCustomerInfo();
        }
    }

    /**
     * Prints all admins in the store.
     */
    public void printAdmins(){
        for (Admin admin : admins) {
            admin.printAdminInfo();
        }
    }

    /**
     * Prints all products in the stock.
     */
    public void printStock(){
        stock.printStock();
    }

    /**
     * Prints all books in the stock.
     */
    public void printStockBook(){
        stock.printStockBook();
    }   

    /**
     * Prints all electronics in the stock.
     */
    public void printStockElectronic(){
        stock.printStockElectronic();
    }       

    /**
     * Prints all fashion in the stock.
     */
    public void printStockFashion(){
        stock.printStockFashion();
    }   

    /**
     * Prints all supermarket in the stock.
     */
    public void printStockSuperMarket(){
        stock.printStockSuperMarket();
    }   

    /**
     * Prints the total stock count.
     */ 
    public void printTotalStock(){
        System.out.println(stock.getTotalStockCount());
    }

    public void addProductToStock(int adminId, Scanner scanner) {
        Admin admin = getAdmin(adminId);
        if(admin == null)
            return;

        System.out.println("Enter Product Category: electronic | book | fashion | supermarket");
        String category = scanner.nextLine();

        switch (category.toLowerCase()) {
            case "electronic" -> {
                System.out.println("Enter Product Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Product Price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter Product Brand:");
                String brand = scanner.nextLine();
                System.out.println("Enter Product Type: SMARTPHONE, LAPTOP, TABLET, TV, CAMERA, HEADPHONE, SPEAKER");
                String type = scanner.nextLine();
                System.out.println("Enter Product Color:");
                String color = scanner.nextLine();
                admin.addProductToStock(new Electronic(name, price, brand, ElectronicProductType.valueOf(type), color), scanner);
            }
                case "book" -> {
                System.out.println("Enter book Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Product Price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter Product Author:");
                String author = scanner.nextLine();
                admin.addProductToStock(new Book(name, price, author), scanner);
            }
                case "fashion" -> {
                System.out.println("Enter fashion Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Product Price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter Product Type:");
                String type = scanner.nextLine();       
                System.out.println("Enter Product Color:");
                String color = scanner.nextLine();
                System.out.println("Enter Product Size:");
                String size = scanner.nextLine();
                admin.addProductToStock(new Fashion(name, price, type, color, size), scanner);
            }

            case "supermarket" -> {
                System.out.println("Enter Product Name:");
                String name = scanner.nextLine();
                System.out.println("Enter Product Price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter Product Type: FRUITS, VEGETABLES, MEAT, FISH, POULTRY, DRINKS, BREAD, DAIRY, EGGS");
                String type = scanner.nextLine();
                admin.addProductToStock(new SuperMarket(name, price, SuperMarketProductType.valueOf(type)), scanner);
            }
            default -> System.out.println("Invalid category");
        }
    }


    public void removeProductFromStock(int adminId, Scanner scanner) {
        Admin admin = getAdmin(adminId);
        if(admin == null)
            return;

        System.out.println("Enter Product Category: electronic | book | fashion | supermarket");
        String category = scanner.nextLine();
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        admin.removeProductFromStock(stock.getProduct(category,name), scanner);
    }

    /**
     * Gets the admin unique identifier.
     * @return The admin instant
     */
    private Admin getAdmin(int adminId) {
        for (Admin ad : admins){
            if(ad.getAdminId() == adminId)
                return ad;
        }
        return null;
    }

    /**
     * Adds a new customer to the store.
     * @param customerName The admin name
     * @param customerPassword The admin password
     */
    public Customer addNewCustomer(String customerName, String customerPassword, String customerPhoneNumber, String customerAddress){
        Customer customer = new Customer(customerPhoneNumber, customerAddress, customerName, customerPassword);
        customers.add(customer);
        return customer;
    }

    public Customer verifyCustomerLogin(String name, String paswrd){
        for (Customer customer : customers) {

            if (name.equals(customer.getUserName())){
                if(customer.verifyPassword(paswrd)) {
                    customer.setIsPasswordChecked(true);
                    return customer;
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }

    public Product findProduct(String category, String name){
        return stock.getProduct(category,name);
    }
}
