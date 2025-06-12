/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Orderentry;
import view.MenuItems;
import view.Orderconfirmation;
import view.Receiptpage;

/**
 *
 * @author ASUS
 */
public class OrderConfirmationcontroller {
    
    private final Orderconfirmation orderconfirmation;
    private final MenuItems menuitem;
    private List<Orderentry> currentOrderList;
    public OrderConfirmationcontroller(Orderconfirmation orderconfirmation,List<Orderentry> currentOrderList,MenuItems menuitem){
        this.currentOrderList=currentOrderList;
        this.orderconfirmation=orderconfirmation;
        this.menuitem=menuitem;
        orderconfirmation.addlistenerReceipt(new addreceiptlistener());
        
    }
    class addreceiptlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            orderconfirmation.dispose();
            Receiptpage receiptwindow = new Receiptpage(menuitem,true,currentOrderList);
            receiptwindow.setVisible(true);
            
        }
        
    
 }
}