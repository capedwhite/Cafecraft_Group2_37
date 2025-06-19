package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Viewfeedbackadmin;
import view.Workersmanage;
import view.admin_sidebar;
import view.editmenu;
import view.login;
import view.reservationadmin;
import controller.ReservationAdminController;
import view.Vieworders;
import view.inventory;

public class admindasboardcontroller {

    private final admin_sidebar admindashboard;

    public admindasboardcontroller(admin_sidebar admindashboard) {
        this.admindashboard = admindashboard;

        admindashboard.addADDeditmenulistener(new additemlistener());
        admindashboard.addADDmanageemployeelistener(new manageeemployee());
        admindashboard.Addlogoutlistener(new logoutbtn());
        admindashboard.Addfeedbacklistener(new feedbacklistener());
        admindashboard.addReservationAdminListener(new openReservationAdmin());
        admindashboard.addviewordersListener(new vieworder());
        admindashboard.addInventoryButtonListener(new OpenInventoryPanel());
    }

    class additemlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editmenu menuadmin = new editmenu();
            menuadmin.setVisible(true);
            admindashboard.dispose();
            ItemController menucontrol = new ItemController(menuadmin);
        }
    }

    class manageeemployee implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            admindashboard.dispose();
            Workersmanage workermanage = new Workersmanage();
            workermanage.setVisible(true);
            WorkersPanelController workercontroll = new WorkersPanelController(workermanage);
        }
    }

    class logoutbtn implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            admindashboard.dispose();
            login Login = new login();
            Login.setVisible(true);
            Logincontroller logincontrol = new Logincontroller(Login);
        }
    }

    class feedbacklistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            admindashboard.dispose();
            Viewfeedbackadmin viewfeedback = new Viewfeedbackadmin();
            viewfeedback.setVisible(true);
            ViewFeedbackcontroller viewfeedbackcontrol = new ViewFeedbackcontroller(viewfeedback);
        }
    }

    class openReservationAdmin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reservationadmin adminView = new reservationadmin();
            ReservationAdminController rc = new ReservationAdminController(adminView);
            adminView.setVisible(true);
            admindashboard.dispose();
        }
    }

    class vieworder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            admindashboard.dispose();
            Vieworders vieworder = new Vieworders();
            vieworder.setVisible(true);
            Viewordercontroller viewcontrol = new Viewordercontroller(vieworder);
        }
    }

    private class OpenInventoryPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inventory inventoryView = new inventory();
            InventoryController inventoryController = new InventoryController(inventoryView);
            inventoryView.setVisible(true);
            admindashboard.dispose();
        }
    }
}
