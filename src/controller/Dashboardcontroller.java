/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuItems;
import view.dashboard;
import view.login;
/**
 *
 * @author ASUS
 */
public class Dashboardcontroller {
    final private dashboard userview;
    final private  Itemdao itemdao;
    public Dashboardcontroller(dashboard userview,Itemdao itemdao ){
        this.userview=userview;
        this.itemdao=itemdao;
        userview.addlogoutlistener(new logoutlistener());
        userview.addMenubtnlistener(new menulistener());
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
    class menulistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          System.out.println("button clickled");
          MenuItems menuitem = new MenuItems();
          UserMenucontroller usermenu = new UserMenucontroller(itemdao,menuitem);
          menuitem.setVisible(true);
        }
        
    }
}
