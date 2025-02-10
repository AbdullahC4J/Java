package com.ab.ecommerce;

import com.ab.ecommerce.users.Customer;

import java.util.Scanner;

public class UserInterface {
    /** The store of the user interface */
    private final Store store = new Store();
    /** The scanner of the user interface */
    private final Scanner scanner = new Scanner(System.in);

    public UserInterface(){
    }

    public void showMainPage(){
        System.out.println("1. Admin Page");
        System.out.println("2. Customer Page");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAdminPage();
                break;
            case 2:
                showCustomerPage();
                break;
            case 3:
                System.exit(0);
        }
        showMainPage();
    }

    public void showAdminPage(){
        System.out.println("Admin Page");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Return to Main Page");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showAdminSignUpPage();
                break;
            case 2:
                showAdminLoginPage();
                break;
            case 3:
                return;
        }   
    }

    public void showAdminSignUpPage(){
        System.out.println("Sign Up New Admin");
        System.out.println("Enter your name: ");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        int adminId = store.addNewAdmin(username, password);
        System.out.println("Admin signed up successfully");
        showAdminOperation(adminId);
    }

    public void showAdminLoginPage() {
        System.out.println("Login as Admin");
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        int adminId = store.verifyAdminLogin(username, password);
        if (adminId == -1) {
            System.out.println("Invalid Username or Password");
            return;
        } else {
            showAdminOperation(adminId);
        }
    }

    public void showAdminOperation(int adminId){
        System.out.println("Admin " + adminId + "Operations");
        System.out.println("1. Show item in stock");
        System.out.println("2. Show Electronics in stock");
        System.out.println("3. Show Books in stock");
        System.out.println("4. Show Fashions in stock");
        System.out.println("5. Show Groceries in stock");
        System.out.println("6. Add new item to stock");
        System.out.println("7. Remove item from stock");
        System.out.println("8. Return to the main page");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> store.printStock();
            case 2 -> store.printStockElectronic();
            case 3 -> store.printStockBook();
            case 4 -> store.printStockFashion();
            case 5 -> store.printStockSuperMarket();
            case 6 -> store.addProductToStock(adminId);
            case 7 -> store.removeProductFromStock(adminId);
            case 8 -> {
                return;
            }
            default -> System.out.println("Invalid Option");
        }

        showAdminOperation(adminId);
    }


    public void showCustomerPage(){
        System.out.println("Customer Page");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Return to Main Page");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showCustomerSignUpPage();
                break;
            case 2:
                showCustomerLoginPage();
                break;
            case 3:
                return;
        }
    }

    public void showCustomerSignUpPage(){
        System.out.println("Sign Up New Customer");
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();

        Customer customer = store.addNewCustomer(username, password, phoneNumber, address);
        System.out.println("Customer signed up successfully");
        showCustomerOperation(customer);
    }

    public void showCustomerLoginPage(){
        System.out.println("Login as Customer");
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = store.verifyCustomerLogin(username, password);
        if (customer == null) {
            System.out.println("Invalid Username or Password");
            return;
        } else {
            showCustomerOperation(customer);
        }
    }

    public void showCustomerOperation(Customer customer){
        System.out.println("Customer " + customer.getUserName() + " Operations\n");
        System.out.println("all items in stock");
        store.printStock();

        System.out.println("1. Add Product To Cart");
        System.out.println("2. Remove Product From Cart");
        System.out.println("7. Checkout");
        System.out.println("8. Exit");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter Product Category");
                String category = scanner.nextLine();
                System.out.println("Enter Product Name");
                String name = scanner.nextLine();
                customer.addProductToCart(store.findProduct(category,name));
                showCustomerOperation(customer);
            }
            case 2 -> {
                System.out.println("Enter Product Category");
                String category = scanner.nextLine();
                System.out.println("Enter Product Name");
                String name = scanner.nextLine();
                customer.removeProductFromCart(store.findProduct(category,name));
                showCustomerOperation(customer);
            }
            case 3 -> {
                customer.checkout();
                return;
            }
            default -> {
                return;
            }
        }
    }
}