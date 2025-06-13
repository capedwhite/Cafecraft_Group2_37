/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Feedback;
import model.Feedbackmodel;

/**
 *
 * @author ASUS
 */
import dao.Feedbackdao;
import javax.swing.JOptionPane;
import view.admin_sidebar;
import view.dashboard;
public class Feedbackcontroller {
    private final Feedback feedback;
    private final Feedbackdao feedbackdao = new Feedbackdao();
    public Feedbackcontroller(Feedback feedback){
        this.feedback=feedback;
        feedback.addsubmitlistener(new submitbtn());
        feedback.addcancellistener(new cancellistener());
}
    class submitbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           System.out.println("submit button clicked");
           try{
           String Feedbacktext = feedback.getFeedbackfield().getText();
           String username = feedback.getusernamefield().getText();
               if (Feedbacktext.isEmpty()) {
                System.out.println("Please fill first fields");
                return;
            }
          
           Feedbackmodel feedbackmodel = new Feedbackmodel(Feedbacktext,username);
            boolean check = feedbackdao.InsertFeedback(feedbackmodel);
        
           if (check){
               JOptionPane.showMessageDialog(feedback,"Feedback added sucessfully");
                   
        }
           }
            catch(Exception ex){
            System.out.println("error adding feedback" + ex.getMessage());
   }
        
        
    }
    }
    class cancellistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         feedback.dispose();
         dashboard Dashboard = new dashboard();
         Dashboard.setVisible(true);
         Dashboardcontroller dashboardcontrol = new Dashboardcontroller(Dashboard);
        }
        
    }
    }
