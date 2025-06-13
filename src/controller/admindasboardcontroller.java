package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Viewfeedbackadmin;
import view.Workersmanage;
import view.admin_sidebar;
import view.editmenu;
import view.login;

/**
 *
 * @author ASUS
 */
public class admindasboardcontroller {
    private final admin_sidebar admindashboard;
    public admindasboardcontroller(admin_sidebar admindashboard){
        this.admindashboard=admindashboard;
        admindashboard.addADDeditmenulistener(new additemlistener());
        admindashboard.addADDmanageemployeelistener(new manageemployee());
        admindashboard.Addlogoutlistener(new logoutbtn());
        admindashboard.Addfeedbacklistener(new feedbacklistener());
    }

   class additemlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
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
                admindashboard.dispose();
               Workersmanage workermanage= new Workersmanage();
               workermanage.setVisible(true);
               WorkersPanelController workercontroll= new WorkersPanelController(workermanage);
              
               
              
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
       class feedbacklistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            admindashboard.dispose();
            Viewfeedbackadmin viewfeedback = new Viewfeedbackadmin();
            viewfeedback.setVisible(true);
             ViewFeedbackcontroller viewfeedbackcontrol = new ViewFeedbackcontroller(viewfeedback);
        }
           
       }
}