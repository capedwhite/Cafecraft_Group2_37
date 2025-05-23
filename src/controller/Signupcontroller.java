/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.userdao;
import model.User;
import view.Signup;
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
    }
    public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
    

class AddUserListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            System.out.println("button clicked");
            String name=userView.getUsernameField().getText();
            String email=userView.getEmailField().getText();
            String password = userView.getPasswordField().getText();
            User user= new User(name,email,password);
            boolean check = userDao.checkUser(user);
            if(check){
                JOptionPane.showMessageDialog(userView,"duplicate user");
            }else{
                userDao.signup(user);
            }
        }catch(Exception ex){
            System.out.println("error adding user" + ex.getMessage());
   }
 }
}
}