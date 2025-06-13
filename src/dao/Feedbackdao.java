/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedbackmodel;

/**
 *
 * @author ASUS
 */
public class Feedbackdao {
    
     Mysqlconnection mysql= new Mysqlconnection();
      
      public boolean InsertFeedback(Feedbackmodel feedbackmodel){
      Connection conn=mysql.openConnection();
          
          String sql = "INSERT INTO feedback(feedback_text,username)VALUES(?,?)";
          try(PreparedStatement pstmt=conn.prepareStatement(sql)){
              
           String username = feedbackmodel.getUserName();
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }
pstmt.setString(1,feedbackmodel.getFeedbacktext());
pstmt.setString(2,username);
int affectedrows=pstmt.executeUpdate();
return affectedrows != 0;
}
    catch(SQLException ex){
Logger.getLogger(Feedbackdao.class.getName()).log(Level.SEVERE,null,ex);
return false;
}
          finally{
mysql.CloseConnection(conn);
}
    
      }
      public List<Feedbackmodel> getfeedbacktext(){
          Connection conn=mysql.openConnection();
          List<Feedbackmodel> feedbacks = new ArrayList();
          String sql="Select * from feedback";
          try(PreparedStatement pstmt=conn.prepareStatement(sql)){
               ResultSet rs = pstmt.executeQuery();
               while(rs.next()) {
                Feedbackmodel feedbackmodel = new Feedbackmodel(
                    rs.getString("feedback_text"),
                    rs.getInt("feedback_id"),
                    rs.getString("username")
                );
                feedbacks.add(feedbackmodel);
                
          }
      }
          catch(Exception ex){
              Logger.getLogger(Feedbackdao.class.getName()).log(Level.SEVERE,null,ex);
          }
          finally{
mysql.CloseConnection(conn);
}
          return feedbacks;
}
}