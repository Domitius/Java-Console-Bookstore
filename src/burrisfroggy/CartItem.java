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
public class CartItem {
    private Product ItemPurchased;
    private int qty;
    
    public CartItem(Book e, int quanity) {
        this.ItemPurchased = e;
        this.qty = quanity;
        System.out.printf("Qty: %s%nItem: %s%n%nPrice: %.02f%nSubTotal: %.02f%n", this.qty, e.name, e.price, (e.price * this.qty));
    }
    
    public CartItem(Game e, int quanity) {
        this.ItemPurchased = e;
        this.qty = quanity;
        System.out.printf("Qty: %s%nItem: %s%n%nPrice: %.02f%nSubTotal: %.02f%n", this.qty, e.name, e.price, (e.price * this.qty));
    }
    
    protected Product getItemPurchased() {
        return ItemPurchased;
    }
    
    protected int getQuanity() {
        return this.qty;
    }
   
}
