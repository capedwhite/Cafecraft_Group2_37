/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Feedbackdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;
import model.Feedbackmodel;
import view.Viewfeedbackadmin;
import view.admin_sidebar;

/**
 *
 * @author ASUS
 */
public class ViewFeedbackcontroller {
    Feedbackdao feedbackdao = new Feedbackdao();
    private final Viewfeedbackadmin viewfeedbackadmin;
    public ViewFeedbackcontroller(Viewfeedbackadmin viewfeedbackadmin){
        this.viewfeedbackadmin = viewfeedbackadmin;
        viewfeedbackadmin.addbackbtn(new backfeedbackbtn());
        loadallfeedbacks();
    }
    public void loadallfeedbacks(){
        List<Feedbackmodel> feedbacks = feedbackdao.getfeedbacktext();
        JPanel feedbackpanel = viewfeedbackadmin.getfeedbacklist();
        feedbackpanel.removeAll();
        for(Feedbackmodel feedback : feedbacks){
            viewfeedbackadmin.addfeedback(feedback);
        }
        feedbackpanel.revalidate();
        feedbackpanel.repaint();
        
}
    class backfeedbackbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            viewfeedbackadmin.dispose();
            admin_sidebar admindashboard = new admin_sidebar();
            admindasboardcontroller admincontrol = new admindasboardcontroller(admindashboard);
            admindashboard.setVisible(true);
        }
        
    }
}
