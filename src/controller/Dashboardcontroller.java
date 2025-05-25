/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.dashboard;
import view.login;
/**
 *
 * @author ASUS
 */
public class Dashboardcontroller {
    final private dashboard userview;
    public Dashboardcontroller(dashboard userview){
        this.userview=userview;
        userview.addlogoutlistener(new logoutlistener());
    }
    class logoutlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            userview.dispose(); // close the current window
            System.out.println("logout clicked");
        login loginView = new login(); // create a new login window
        Logincontroller controller = new Logincontroller(loginView); // controller
        controller.open(); // show the login window again
        }
        
    }
}
