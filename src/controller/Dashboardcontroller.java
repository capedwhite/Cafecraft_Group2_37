package controller;

import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import view.MenuItems;
//import view.MenuItems;
import view.dashboard;
import view.login;
import view.reservation;
import view.useritempanel;

public class Dashboardcontroller {
    private final dashboard userview;
    private final Itemdao itemdao;

    public Dashboardcontroller(dashboard userview, Itemdao itemdao) {
        this.userview = userview;
        this.itemdao = itemdao;

        // Attach action listeners
        userview.addlogoutlistener(new LogoutListener());
        userview.addMenubtnlistener(new MenuListener()); // ✅ CORRECT class name
           userview.addReservationBtnListener(new ReservationListener()); // ✅ change made by prajal
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
           MenuItems menuitem = new MenuItems(); 
           List<useritempanel> itempanelList = new ArrayList<>();
           UserMenucontroller menucontrol = new UserMenucontroller(itemdao,menuitem,itempanelList);            
        menuitem.setVisible(true); 
        }
    }
    // ✅ change made by prajal - open reservation form
class ReservationListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Reservation button clicked");
        reservation reserveView = new reservation();
        ReservationController rc = new ReservationController(reserveView);
        reserveView.setVisible(true);
    }}
}
