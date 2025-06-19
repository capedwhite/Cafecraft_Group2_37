/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Orderdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import model.Orderjoin;
import view.Placedorderreuse;
import view.Vieworders;
import view.admin_sidebar;

/**
 *
 * @author ASUS
 */
public class Viewordercontroller {
    private final Orderdao orderdao = new Orderdao();
    private final Vieworders vieworder;
    public Viewordercontroller(Vieworders vieworder){
        this.vieworder=vieworder;
        
        loadAllItemsToPanel();
        vieworder.addexitListener(new exitbutton());
        
}
    public void loadAllItemsToPanel() {
        List<Orderjoin> orderjoin = orderdao.getAllPendingOrders(); 
        JPanel orderpanel = vieworder.getpanel(); 

        orderpanel.removeAll();
        for (Orderjoin order : orderjoin) {
           Placedorderreuse card = vieworder.addorder(order); 
           Reuseorderadminpanelcontroller reuseorder = new Reuseorderadminpanelcontroller(card,Viewordercontroller.this);
           
        }

        orderpanel.revalidate();
        orderpanel.repaint();
    }
    class exitbutton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           vieworder.dispose();
           admin_sidebar admindashboard = new admin_sidebar();
           admindasboardcontroller admincontrol = new admindasboardcontroller(admindashboard);
           admindashboard.setVisible(true);
        }
        
    }
}