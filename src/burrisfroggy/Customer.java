/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burrisfroggy;
import java.util.ArrayList;
/**
 *
 * @author Gavin Burris
 */
public class Customer {
    private String name;
    ArrayList<CartItem> shoppingCart = new ArrayList();
    
    public Customer(String name) {
        this.name = name;
    }
    
    protected String getName() {
        return this.name;
    }
    
}
