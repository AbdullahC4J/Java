package com.ab.ecommerce.stock;

import com.ab.ecommerce.products.*;
import java.util.ArrayList;

/**
 * Manages the inventory of all products in the e-commerce system.
 * Handles adding, removing, and querying stock levels for different product types.
 */
public final class Stock {
    /** List to store book products in stock */
    private final ArrayList<Book> stockBookList = new ArrayList<>();
    
    /** List to store electronic products in stock */
    private final ArrayList<Electronic> stockElectronicList = new ArrayList<>();
    
    /** List to store fashion products in stock */
    private final ArrayList<Fashion> stockFashionList = new ArrayList<>();
    
    /** List to store supermarket products in stock */
    private final ArrayList<SuperMarket> stockSuperMarketList = new ArrayList<>();

    /**
     * Constructs a new Stock.
     */
    public Stock() {

        /* Adding default products to the stock */
        stockBookList.add(new Book("The Algorithm Design Manual", 350.0, "Skiena"));
        stockElectronicList.add(new Electronic("Iphone 13", 25500.0, "Apple", ElectronicProductType.SMARTPHONE, "Black"));
        stockFashionList.add(new Fashion("Shirt", 320.0, "shirt", "navy", "Xl"));
        stockSuperMarketList.add(new SuperMarket("Milk", 35.8,SuperMarketProductType.DAIRY));
    }

    /**
     * Adds a product to the stock list based on its type.
     * @param product The product to add to stock
     * @throws IllegalArgumentException if the product type is invalid
     */
    public boolean addProduct(Product product) {
        return switch (product) {
            case Book book -> stockBookList.add(book);
            case Electronic electronic -> stockElectronicList.add(electronic);
            case Fashion fashion -> stockFashionList.add(fashion);
            case SuperMarket superMarket -> stockSuperMarketList.add(superMarket);
            default -> throw new IllegalArgumentException("Invalid Product Type");
        };
    }

    /**
     * Removes a product from the stock list based on its type.
     * @param product The product to remove from stock
     * @throws IllegalArgumentException if the product type is invalid
     */
    public boolean removeProduct(Product product) {
        return switch (product) {
            case Book book -> stockBookList.remove(book);
            case Electronic electronic -> stockElectronicList.remove(electronic);
            case Fashion fashion -> stockFashionList.remove(fashion);
            case SuperMarket superMarket -> stockSuperMarketList.remove(superMarket);
            default -> throw new IllegalArgumentException("Invalid Product Type");
        };
    }

    /**
     * Prints the current stock levels for all product types.
     * Shows the number of items in each category.
     */
    public void printStock() {      
        System.out.println("Stock:\n");
        System.out.println("Books: " + stockBookList.size() + "\n");
        printStockBook();
        System.out.println("Electronic: " + stockElectronicList.size() + "\n");
        printStockElectronic();
        System.out.println("Fashion: " + stockFashionList.size() + "\n");
        printStockFashion();
        System.out.println("SuperMarket: " + stockSuperMarketList.size());
        printStockSuperMarket();
        System.out.println("**********************************************************");
    }   

    /**
     * Prints detailed information for all books in stock.
     */
    public void printStockBook() {
        for (int i = 0; i < stockBookList.size(); i++) {
            System.out.println("Book " + i+1 + " :");
            stockBookList.get(i).printProductInfo();
        }
    }

    /**
     * Prints detailed information for all electronic items in stock.
     */
    public void printStockElectronic() {
        for (int i = 0; i < stockElectronicList.size(); i++) {
            System.out.println("Electronic " + i+1 + " :");
            stockElectronicList.get(i).printProductInfo();
        }
    }       

    /**
     * Prints detailed information for all fashion items in stock.
     */
    public void printStockFashion() {
        for (int i = 0; i < stockFashionList.size(); i++) {
            System.out.println("Fashion " + i+1 + " :");
            stockFashionList.get(i).printProductInfo();
        }
    }      

    /**
     * Prints detailed information for all supermarket items in stock.
     */
    public void printStockSuperMarket() {
        for (int i = 0; i < stockSuperMarketList.size(); i++) {
            System.out.println("SuperMarket " + i+1 + " :");
            stockSuperMarketList.get(i).printProductInfo();
        }
    }              
    
    /**
     * Gets the total count of all items in stock.
     * @return Total number of items across all categories
     */
    public int getTotalStockCount() {
        return stockBookList.size() + stockElectronicList.size() + 
               stockFashionList.size() + stockSuperMarketList.size();
    }
    
    /**
     * Gets the count of books in stock.
     * @return Number of books in stock
     */
    public int getBookCount() {
        return stockBookList.size();
    }

    /**
     * Gets the count of electronic items in stock.
     * @return Number of electronic items in stock
     */
    public int getElectronicCount() {
        return stockElectronicList.size();
    }           

    /**
     * Gets the count of fashion items in stock.
     * @return Number of fashion items in stock
     */
    public int getFashionCount() {
        return stockFashionList.size();
    }

    /**
     * Gets the count of supermarket items in stock.
     * @return Number of supermarket items in stock
     */
    public int getSupermarketCount() {
        return stockSuperMarketList.size();
    }

    /**
     * Get a product from the stock list based on its type.
     * @param category The product category to retrieve from stock
     * @param name The product name to retrieve from stock
     * @return Product from the stock
     * @throws IllegalArgumentException if the product type is invalid
     */
    public Product getProduct(String category, String name) {
        switch (category.toLowerCase()) {
            case "book" -> {for(Book b : stockBookList) if(name.equals(b.getName())) return b;}
            case "electronic" -> {for(Electronic e : stockElectronicList) if(name.equals(e.getName())) return e;}
            case "fashion" -> {for(Fashion f : stockFashionList) if(name.equals(f.getName())) return f;}
            case "supermarket" -> {for(SuperMarket s : stockSuperMarketList) if(name.equals(s.getName())) return s;}
            default -> throw new IllegalArgumentException("Invalid Product Type");
        }

        return null;
    }
}