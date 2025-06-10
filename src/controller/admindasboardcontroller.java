package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.admin_sidebar;
import view.editmenu;
import view.aboutcafe;
import controller.AboutCafeController;

public class admindasboardcontroller {

    private final admin_sidebar admindashboard;

    public admindasboardcontroller(admin_sidebar admindashboard) {
        this.admindashboard = admindashboard;

        System.out.println("Admin Dashboard Loaded");

        admindashboard.addEditmenubtnListener(new additemlistener());
        admindashboard.addAboutCafeListener(new AboutCafeListener());
        admindashboard.testCafeButtonWiring();
    }

    class additemlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editmenu menuadmin = new editmenu();
            menuadmin.setVisible(true);
        }
    }
    //addeed by prajal 
    // this below part to call about cafe in adminside 

    class AboutCafeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("About Cafe button clicked (Admin)");
            aboutcafe aboutView = new aboutcafe();
           AboutCafeController aboutcafe =  new AboutCafeController(aboutView);
            aboutView.setVisible(true);
        }
    }
}
