package controller;

import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import view.Feedback;
import view.MenuItems;
import view.Userorderhistory;
import view.aboutcafe;
//import view.MenuItems;
import view.dashboard;
import view.login;
import view.reservation;
import view.useritempanel;

public class Dashboardcontroller {
    private final dashboard userview;
    private final Itemdao itemdao = new Itemdao();

    public Dashboardcontroller(dashboard userview) {
        this.userview = userview;
        

        // Attach action listeners
        userview.addlogoutlistener(new LogoutListener());
        userview.addMenubtnlistener(new MenuListener()); 
        userview.feedbackbtnlistener(new feedbacklistener());
        userview.addaboutcafelistener(new aboutcafelistener());
        userview.addReservationBtnListener(new ReservationListener());
        userview.addOrderhistorylistener(new orderlistener());
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
            userview.dispose();
            System.out.println("Menu button clicked");
           MenuItems menuitem = new MenuItems(); 
           List<useritempanel> itempanelList = new ArrayList<>();
           UserMenucontroller menucontrol = new UserMenucontroller(itemdao,menuitem,itempanelList);            
        menuitem.setVisible(true); 
        }
    }
    class feedbacklistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           userview.dispose();
           Feedback feedback = new Feedback();
           feedback.setVisible(true);
           Feedbackcontroller feedbackcontrol = new Feedbackcontroller(feedback);
        }
        
    }
    class aboutcafelistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            userview.dispose();
            aboutcafe Aboutcafe = new aboutcafe();
            Aboutcafe.setVisible(true);
            Aboutcafecontroller aboutcafecontrol = new Aboutcafecontroller(Aboutcafe);
            
        }
        
    }
    class ReservationListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Reservation button clicked");
        reservation reserveView = new reservation();
        ReservationController rc = new ReservationController(reserveView);
        reserveView.setVisible(true);
    }}
    class orderlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          userview.dispose();
            
          System.out.println("orderhistory btn clicked");
          Userorderhistory orderhistory = new Userorderhistory();
          orderhistory.setVisible(true);
          Orderhistorycontroller orderhistorycontrol = new Orderhistorycontroller(orderhistory);
        }
        
    }
}
