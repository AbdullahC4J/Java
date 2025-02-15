package com.ab.ecommerce;

import com.ab.ecommerce.users.Customer;

import java.util.Scanner;

public class UserInterface {
    /** The store of the user interface */
    private final Store store = new Store();
    /** The scanner of the user interface */
    private final Scanner scanner = new Scanner(System.in);


    public void showMainPage(){
        System.out.println("**********************************************************");
        System.out.println("1. Admin Page");
        System.out.println("2. Customer Page");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("**********************************************************");
        switch (choice) {
            case 1:
                showAdminPage();
                break;
            case 2:
                showCustomerPage();
                break;
            case 3:
                scanner.close();
                System.exit(0);
                break;
        }
        showMainPage();
    }

    public void showAdminPage(){
        System.out.println("Admin Page");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Return to Main Page");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("**********************************************************");
        switch (choice) {
            case 1:
                showAdminSignUpPage();
                break;
            case 2:
                showAdminLoginPage();
                break;
            default:
                System.out.println("\nReturning to the Main Page.........\n");
        }   
    }

    public void showAdminSignUpPage(){
        System.out.println("Sign Up New Admin");
        System.out.println("Enter your name: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        int adminId = store.addNewAdmin(username, password);
        System.out.println("Admin signed up successfully");
        System.out.println("**********************************************************");
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
        } else {
            showAdminOperation(adminId);
        }
    }

    public void showAdminOperation(int adminId){
        System.out.println("Admin " + adminId + " Operations");
        System.out.println("1. Show item in stock");
        System.out.println("2. Show Electronics in stock");
        System.out.println("3. Show Books in stock");
        System.out.println("4. Show Fashions in stock");
        System.out.println("5. Show Groceries in stock");
        System.out.println("6. Add new item to stock");
        System.out.println("7. Remove item from stock");
        System.out.println("8. Return to the main page");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("**********************************************************");
        switch (choice) {
            case 1 -> store.printStock();
            case 2 -> store.printStockElectronic();
            case 3 -> store.printStockBook();
            case 4 -> store.printStockFashion();
            case 5 -> store.printStockSuperMarket();
            case 6 -> store.addProductToStock(adminId, scanner);
            case 7 -> store.removeProductFromStock(adminId, scanner);
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
        scanner.nextLine(); // consume newline
        System.out.println("**********************************************************");

        switch (choice) {
            case 1:
                showCustomerSignUpPage();
                break;
            case 2:
                showCustomerLoginPage();
                break;
            default:
                System.out.println("\nReturning to the Main Page.........\n");
        }
    }

    public void showCustomerSignUpPage(){
        System.out.println("""
                **********************
                Sign Up New Customer
                **********************
                """);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter your address: ");
        String address = scanner.nextLine();

        Customer customer = store.addNewCustomer(username, password, phoneNumber, address);
        System.out.println("Customer signed up successfully\n");
        System.out.println("**********************************************************");
        showCustomerOperation(customer);
    }

    public void showCustomerLoginPage(){
        System.out.println("""
                ******************
                Login as Customer
                ******************
                """);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        Customer customer = store.verifyCustomerLogin(username, password);
        if (customer == null) {
            System.out.println("Invalid Username or Password");
        } else {
            showCustomerOperation(customer);
        }
    }

    public void showCustomerOperation(Customer customer){
        System.out.println("************************************************");
        System.out.println("Customer" + customer.getUserName() + "Operations");
        System.out.println("************************************************");
        store.printStock();

        System.out.println("1. Add Product To Cart");
        System.out.println("2. Remove Product From Cart");
        System.out.println("3. Show Products in The Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1 -> {
                System.out.println("Enter Product Category: electronic | book | fashion | supermarket\n");
                String category = scanner.nextLine();
                System.out.println("Enter Product Number : ");
                int index = scanner.nextInt();
                scanner.nextLine(); // consume newline
                customer.addProductToCart(store.findProduct(category,index));
                showCustomerOperation(customer);
            }
            case 2 -> {
                System.out.println("Enter Product Category: electronic | book | fashion | supermarket\n");
                String category = scanner.nextLine();
                System.out.println("Enter Product Number : ");
                int index = scanner.nextInt();
                scanner.nextLine(); // consume newline
                customer.removeProductFromCart(store.findProduct(category,index));
                showCustomerOperation(customer);
            }
            case 3 -> {
                customer.printCustomerCart();
                showCustomerOperation(customer);
            }
            case 4 -> customer.checkout(scanner);

            default -> {}
        }
    }
}