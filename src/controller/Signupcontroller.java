/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.Itemdao;
import dao.userdao;
import model.User;
import view.Signup;
import view.dashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.login;

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
        userView.addbackbtnlistener(new backbtn());
    }
    public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
    

class AddUserListener implements ActionListener{
      public boolean authenticatesignin(String name, String password,String Securityqn,String Answer) {
        return !(name.isEmpty() || password.isEmpty());
    }
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            System.out.println("button clicked");
            String name=userView.getUsernameField().getText();
            String Securityqn=userView.getQuestionField().getText();
            String Answer = userView.getanswerField().getText();
            String password = userView.getPasswordField().getText();
            User user= new User(name,Securityqn,Answer,password);
            boolean check = userDao.checkUser(user);
            if(! authenticatesignin(name,password,Securityqn,Answer)){
                 JOptionPane.showMessageDialog(userView,"Cant leave empty fields");
                 return ;
            }
            if(check){
                JOptionPane.showMessageDialog(userView,"duplicate user");

            }else{
                userDao.signup(user);
                  JOptionPane.showMessageDialog(userView, "Signup successful!");
                  login Login = new login();
                  Login.setVisible(true);
                  Logincontroller logincontrol = new Logincontroller(Login);
               
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
class backbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           userView.dispose();
           login Login = new login();
           Login.setVisible(true);
           Logincontroller logincontrol = new Logincontroller(Login);
                   
        }
    
}
}