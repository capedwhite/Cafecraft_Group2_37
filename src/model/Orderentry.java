/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Orderentry {
    int itemId;
    String productName;
        double unitPrice;
        int qty;
        double total;

        public Orderentry(String name, double price, int quantity,int itemId) {
            this.productName = name;
            this.unitPrice = price;
            this.qty = quantity;
            this.total = price * quantity;
            this.itemId= itemId;
        }
        public String getProductName() {
    return productName;
}

public double getUnitPrice() {
    return unitPrice;
}

public int getQty() {
    return qty;
}

public double getTotal() {
    return total;
}

public int getItemId() {
    return itemId;
}
    }

