/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author user
 */
package controller;
import dao.Itemdao;
import dao.logindao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.User;
import view.ForgotPassword;
import view.Signup;
import view.admin_sidebar;
import view.dashboard;
import view.login;

/**
 *
 * @author user
 */
public class Logincontroller {
    private final logindao Logindao=new logindao();
    private final login userView;
    
    public Logincontroller (login userView){
        this.userView = userView;
        
        userView.addloginListener(new AddUserListener());
        userView.addswitchtosigninlistener(new addswitchtosignin());
        userView.addforgetpasswordlistener (new addforgetpassword());
        userView.addshowpasswordlistener(new showpassword());
    }
    public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
        public boolean authenticate(String name, String password) {
        return !(name.isEmpty() || password.isEmpty());
    }
class AddUserListener implements ActionListener{
        
     @Override
    public void actionPerformed(ActionEvent e){
         try{
            System.out.println("button clicked");
            String name=userView.getUsernameFieldd().getText();
            String password=userView.getPasswordFieldd().getText();
            User user = new User(name, password);
            boolean check =  Logindao.validateUser(user);
            if(! authenticate(name,password)){
                JOptionPane.showMessageDialog(userView,"cant leave empty fields");
                return;
            }
            
            if(check){
            JOptionPane.showMessageDialog(userView,"Login sucessfull");
           
               if("admin".equals(Logindao.Checkrole(user))){
                   Sessioncontroller.setcurrentuser(user.getID());
                   admin_sidebar admindashboard = new admin_sidebar();    
                   admindashboard.setVisible(true);
                   admindasboardcontroller admindashboardcontrol = new admindasboardcontroller(admindashboard);
               }
               else{
                   Sessioncontroller.setcurrentuser(user.getID());
                   dashboard Dashboard = new dashboard();
                   Itemdao itemDao = new Itemdao();
                   Dashboard.setVisible(true);
                   Dashboardcontroller dashboardcontrol = new Dashboardcontroller(Dashboard,itemDao);
               }
    close();
            }
            else{
               JOptionPane.showMessageDialog(userView,"cannot find username");
              }
            }
        
          catch(Exception ex){
            System.out.println("error adding user" + ex.getMessage());
        }
    }
}

class addswitchtosignin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
      Signup signup = new Signup(); 
      Signupcontroller signupController = new Signupcontroller(signup); // Controller
signupController.open();
    signup.setVisible(true);
      close();
        }
    
}
class addforgetpassword implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String name=userView.getUsernameFieldd().getText();
            ForgotPassword forgotpassword = new ForgotPassword();
            ForgotPasswordController controller = new ForgotPasswordController(forgotpassword,name);     
        }
    
}
class showpassword implements ActionListener{
   boolean passwordvisible = false;
        @Override
        public void actionPerformed(ActionEvent e) {
                passwordvisible=!passwordvisible;
                if(passwordvisible){
                userView.getPasswordFieldd().setEchoChar((char) 0); // Show characters
                }
                else{
                userView.getPasswordFieldd().setEchoChar('\u2022');
                }

        }        
}
}
