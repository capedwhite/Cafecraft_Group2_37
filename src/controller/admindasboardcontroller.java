/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.admin_sidebar;
import view.editmenu;

/**
 *
 * @author ASUS
 */
public class admindasboardcontroller {
    private final admin_sidebar admindashboard;
    public admindasboardcontroller(admin_sidebar admindashboard){
        this.admindashboard=admindashboard;
        admindashboard.addADDeditmenulistener(new additemlistener());
    }
   class additemlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           editmenu menuadmin = new editmenu(); 
    menuadmin.setVisible(true);
    ItemController menucontrol = new ItemController(menuadmin);
        }
       
   }
}
