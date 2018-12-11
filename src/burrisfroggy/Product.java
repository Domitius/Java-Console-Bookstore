/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burrisfroggy;

/**
 *
 * @author Gavin Burris
 */
public class Product {
    private String ItemCode;
    String name;
    double price;
    String description;

    public Product(String ItemCode, String name, double price) {
        // Setting Item Code, it needs a setter since it is a private variable
        setItemCode(ItemCode);
        this.name = name;
        this.price = price;
        this.description = null;
    }
    
    public Product(String ItemCode, String name, double price, String description) {
        // Setting Item Code, it needs a setter since it is a private variable
        setItemCode(ItemCode);
        this.name = name;
        this.price = price;
        this.description = description;
    }
    
    protected void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }
    
    protected String getItemCode() {
        return this.ItemCode;
    }
    
}
