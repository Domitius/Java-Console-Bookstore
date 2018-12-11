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
public class Game extends Product {
    private String rating;
    
    public Game(String ItemCode, String name, double price, String rating) {
        super(ItemCode, name, price);
        setRating(rating);
    }
    
    public Game(String ItemCode, String name, String description, double price, String rating) {
        super(ItemCode, name, price, description);
        setRating(rating);
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    public String displayGame() {
        return this.getItemCode() + "  " + this.name;
    }
}
