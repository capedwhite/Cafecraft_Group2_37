package controller;

import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuItems;
import view.dashboard;
import view.login;

public class Dashboardcontroller {
    private final dashboard userview;
    private final Itemdao itemdao;

    public Dashboardcontroller(dashboard userview, Itemdao itemdao) {
        this.userview = userview;
        this.itemdao = itemdao;

        // Attach action listeners
        userview.addlogoutlistener(new LogoutListener());
        userview.addMenubtnlistener(new MenuListener()); // ✅ CORRECT class name
    }

    // ✅ Handles logout and opens login again
    class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userview.dispose(); // close current dashboard
            System.out.println("Logout clicked");

            login loginView = new login(); // show login again
            Logincontroller controller = new Logincontroller(loginView);
            controller.open();
        }
    }
    //changes made by prajal 
    // ✅ Opens MenuItems and wires up all logic
    class MenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Menu button clicked");

            MenuItems menuitem = new MenuItems(); // View
            UserMenucontroller menucontrol=new  UserMenucontroller(itemdao, menuitem); // loads item cards
            //new MenuitemsController(menuitem);         // wires Reset/Confirm/Receipt
            
            menuitem.setVisible(true); // Show UI
        }
    }
}
