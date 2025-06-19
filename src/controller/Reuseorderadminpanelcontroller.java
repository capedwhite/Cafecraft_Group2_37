/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Orderdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.Placedorderreuse;

/**
 *
 * @author ASUS
 */
public class Reuseorderadminpanelcontroller {
   private final Placedorderreuse placeorder;
   private final Viewordercontroller viewordercontrol;
   Orderdao orderdao = new Orderdao();
    public Reuseorderadminpanelcontroller(Placedorderreuse placeorder,Viewordercontroller viewordercontrol){
        this.placeorder=placeorder;
        this.viewordercontrol=viewordercontrol;
        placeorder.addcompletedactionlistener(new completelistener());
    }
    class completelistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            int orderid = Integer.parseInt(placeorder.getOrderid().getText());
           boolean Check = orderdao.updatestatus(orderid);
           
           if(Check){
                JOptionPane.showMessageDialog(null, "Order marked as completed!");
                viewordercontrol.loadAllItemsToPanel();
        }
            else {
            JOptionPane.showMessageDialog(null, "Failed to update order status.");
        }
    
        
    }
}
}
