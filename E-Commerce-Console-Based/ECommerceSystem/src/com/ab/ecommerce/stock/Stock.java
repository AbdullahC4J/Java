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
    }

    /**
     * Adds a product to the stock list based on its type.
     * @param product The product to add to stock
     * @throws IllegalArgumentException if the product type is invalid
     */
    public boolean addProduct(Product product) {
        switch (product) {
            case Book book -> {
                return stockBookList.add(book);
            }
            case Electronic electronic -> {
                return stockElectronicList.add(electronic);
            }
            case Fashion fashion -> {
                return stockFashionList.add(fashion);
            }
            case SuperMarket superMarket -> {
                return stockSuperMarketList.add(superMarket);
            }

            default -> throw new IllegalArgumentException("Invalid Product Type");
        }
    }

    /**
     * Removes a product from the stock list based on its type.
     * @param product The product to remove from stock
     * @throws IllegalArgumentException if the product type is invalid
     */
    public boolean removeProduct(Product product) {
        switch (product) {
            case Book book -> {return stockBookList.remove(book);}
            case Electronic electronic -> {return stockElectronicList.remove(electronic);}
            case Fashion fashion -> {return stockFashionList.remove(fashion);}
            case SuperMarket superMarket -> {return stockSuperMarketList.remove(superMarket);}

            default -> throw new IllegalArgumentException("Invalid Product Type");
        }
    }

    /**
     * Prints the current stock levels for all product types.
     * Shows the number of items in each category.
     */
    public void printStock() {      
        System.out.println("Stock:\n" + "Books: " + stockBookList.size() + "\n" + 
            "Electronic: " + stockElectronicList.size() + "\n" + 
            "Fashion: " + stockFashionList.size() + "\n" + 
            "SuperMarket: " + stockSuperMarketList.size());
    }   

    /**
     * Prints detailed information for all books in stock.
     */
    public void printStockBook() {
        for (Book book : stockBookList) {
            book.printProductInfo();
        }
    }

    /**
     * Prints detailed information for all electronic items in stock.
     */
    public void printStockElectronic() {
        for (Electronic electronic : stockElectronicList) {
            electronic.printProductInfo();
        }
    }       

    /**
     * Prints detailed information for all fashion items in stock.
     */
    public void printStockFashion() {
        for (Fashion fashion : stockFashionList) {
            fashion.printProductInfo();
        }
    }      

    /**
     * Prints detailed information for all supermarket items in stock.
     */
    public void printStockSuperMarket() {
        for (SuperMarket superMarket : stockSuperMarketList) {
            superMarket.printProductInfo();
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