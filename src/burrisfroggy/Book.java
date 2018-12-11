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
public class Book extends Product {
    private String author;
    
    public Book(String ItemCode, String name, double price, String author) {
        super(ItemCode, name, price);
        setAuthor(author);
    }
    
    public Book(String ItemCode, String name, String description, double price, String author) {
        super(ItemCode, name, price, description);
        this.author = author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String displayBook() {
        String text = this.getItemCode() + "  " + this.name;
        return text;
    }
}
