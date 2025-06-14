package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Workersmanage;
import view.admin_sidebar;
import view.editmenu;
import view.login;
import view.reservationadmin;                      // ✅ added by Prajal
import controller.ReservationAdminController;     // ✅ added by Prajal

/**
 *
 * @author ASUS
 */
public class admindasboardcontroller {

    private final admin_sidebar admindashboard;

    public admindasboardcontroller(admin_sidebar admindashboard) {
        this.admindashboard = admindashboard;

        admindashboard.addADDeditmenulistener(new additemlistener());
        admindashboard.addADDmanageemployeelistener(new manageemployee());
        admindashboard.Addlogoutlistener(new logoutbtn());
        admindashboard.addReservationAdminListener(new openReservationAdmin()); // ✅ added by Prajal
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

    class manageemployee implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            Workersmanage workermanage = new Workersmanage();
            workermanage.setVisible(true);
            WorkersPanelController workercontroll = new WorkersPanelController(workermanage);
            admindashboard.dispose();
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

    // ✅ added by Prajal - opens admin reservation table view
    class openReservationAdmin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reservationadmin adminView = new reservationadmin(); // JFrame with table
            ReservationAdminController rc = new ReservationAdminController(adminView); // controller binds it
            adminView.setVisible(true);
            admindashboard.dispose();
        }
    }
}
