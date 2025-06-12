package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import view.Workersmanage;
import view.admin_sidebar;
import view.editmenu;
import view.login;
=======

import view.admin_sidebar;
import view.editmenu;
import view.aboutcafe;
import controller.AboutCafeController;
>>>>>>> origin/aboutcafe_function

public class admindasboardcontroller {
<<<<<<< HEAD
    private final admin_sidebar admindashboard;
    public admindasboardcontroller(admin_sidebar admindashboard){
        this.admindashboard=admindashboard;
        admindashboard.addADDeditmenulistener(new additemlistener());
        admindashboard.addADDmanageemployeelistener(new manageemployee());
        admindashboard.Addlogoutlistener(new logoutbtn());
    }

   class additemlistener implements ActionListener{
=======
>>>>>>> origin/aboutcafe_function

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
<<<<<<< HEAD
           editmenu menuadmin = new editmenu(); 
    menuadmin.setVisible(true);
    admindashboard.dispose();
    ItemController menucontrol = new ItemController(menuadmin);
        }
   }
       class manageemployee implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button clicked");
               Workersmanage workermanage= new Workersmanage();
               workermanage.setVisible(true);
               WorkersPanelController workercontroll= new WorkersPanelController(workermanage);
              admindashboard.dispose();
              
       }
        
   }
       class logoutbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           admindashboard.dispose();
           login Login = new login();
           Login.setVisible(true);
           Logincontroller logincontrol = new Logincontroller(Login);
           
        }
           
       }
=======
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
>>>>>>> origin/aboutcafe_function
}

