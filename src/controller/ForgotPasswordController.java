/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ForgotPasswordDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.ForgotPassword;

public class ForgotPasswordController {
    private final ForgotPasswordDAO Forgotdao = new ForgotPasswordDAO();
    private final ForgotPassword userView;
    private final String username;

    public ForgotPasswordController(ForgotPassword view, String name) {
        this.userView = view;
        this.username = name;
        userView.addForgetpasswordlistener(new addchangedpassword());
         String question = Forgotdao.getSecurityQuestion(username);
        if (question != null) {
            userView.setsecurityquestion(question);
            userView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Username not found");
        }

    }
 public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }

class addchangedpassword implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
         try{
            System.out.println("button clicked");
        String password = userView.getnewpassword().getText();
        String answer = userView.getanswer().getText();
        if(password.trim().isEmpty()){
            JOptionPane.showMessageDialog(userView,"Password cannot be null");
            return;
        }
        if( Forgotdao.checkSecurityAnswer(username,answer)){
            Forgotdao.updatePassword(password,username);
            close();
         }
        else{
            JOptionPane.showMessageDialog(userView,"Incorrect security answer");
            
        }
    }
         
    catch(Exception ex){
            System.out.println("error adding user" + ex.getMessage());
        }
         
    

    }
}
}

    

   
