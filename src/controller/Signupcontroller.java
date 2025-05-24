/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.userdao;
import model.User;
import view.Signup;
import view.dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Signupcontroller {
    private final userdao userDao=new userdao();
    private final Signup userView;
    
    public Signupcontroller(Signup userView){
        this.userView = userView;
        
        userView.addSignupListener(new AddUserListener());
        userView.addshowpasswordlistener(new showpassword());
    }
    public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
    

class AddUserListener implements ActionListener{
      public boolean authenticatesignin(String name, String password,String email) {
        return !(name.isEmpty() || password.isEmpty());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            System.out.println("button clicked");
            String name=userView.getUsernameField().getText();
            String email=userView.getEmailField().getText();
            String password = userView.getPasswordField().getText();
            User user= new User(name,email,password);
            boolean check = userDao.checkUser(user);
            if(! authenticatesignin(name,password,email)){
                 JOptionPane.showMessageDialog(userView,"Cant leave empty fields");
            }
            if(check){
                JOptionPane.showMessageDialog(userView,"duplicate user");
            }else{
                userDao.signup(user);
                  JOptionPane.showMessageDialog(userView, "Signup successful!");
                dashboard Dashboard = new dashboard(); // replace with your actual dashboard class
    Dashboard.setVisible(true);
    close();
            }
        }
        catch(Exception ex){
            System.out.println("error adding user" + ex.getMessage());
   }
        
 }
    
}
class showpassword implements ActionListener{
   boolean passwordvisible = false;
        @Override
        public void actionPerformed(ActionEvent e) {
                passwordvisible=!passwordvisible;
                if(passwordvisible){
                userView.getPasswordField().setEchoChar((char) 0); // Show characters
                }
                else{
                userView.getPasswordField().setEchoChar('\u2022');
                }
}
}
}