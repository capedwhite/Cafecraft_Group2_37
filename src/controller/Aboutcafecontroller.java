/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.aboutcafe;
import view.dashboard;

/**
 *
 * @author ASUS
 */
public class Aboutcafecontroller {
    private final aboutcafe Aboutcafe;
    public Aboutcafecontroller(aboutcafe Aboutcafe){
        this.Aboutcafe= Aboutcafe;
        Aboutcafe.Exitbtnlistener(new exitbutton());
}
    class exitbutton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           Aboutcafe.dispose();
           dashboard Dashboard= new dashboard();
           Dashboardcontroller dashboardcontrol = new Dashboardcontroller(Dashboard);
           Dashboard.setVisible(true);
        }
        
}
}
