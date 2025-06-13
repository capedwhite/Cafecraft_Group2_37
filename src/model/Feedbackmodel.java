/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Feedbackmodel {
    String feedback;
    int feedback_id;
    String username;
    public Feedbackmodel(String feedback,int feedback_id,String username){
        this.feedback=feedback;
        this.feedback_id=feedback_id;
        this.username=username;
    }
    public Feedbackmodel(String feedback,String username){
        this.feedback = feedback;
        this.username = username;
    }
     public int getID() {
        return feedback_id;
    }
    public String getUserName() {
        return username;
    }

    public String getFeedbacktext(){
        return feedback;
    }

    
}
