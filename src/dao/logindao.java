/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
package dao;
import database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import model.User;


/**
 *
 * @author user
 */
public class logindao {
 Mysqlconnection mysql=new Mysqlconnection();
 
 
    public boolean validateUser(User user) {
       Connection conn = mysql.openConnection();
        String sql =  "SELECT * FROM users WHERE username=? AND password=?";
        boolean isValid = false;
      try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
           try (ResultSet rs = ps.executeQuery()) {
               isValid = rs.next();
               
           }
            ps.close();
            conn.close();
        } 
      catch (Exception e) {
     Logger.getLogger(logindao.class.getName()).log(Level.SEVERE,null,e);
        }

        return isValid;
    }

public String Checkrole(User user) {
    Connection conn = mysql.openConnection();
    String sql = "SELECT id,role FROM users WHERE username = ? AND password = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            user.setID(id);
            return rs.getString("role"); // returns 'admin' or 'user'
            
            
        } else {
            return null; // invalid login
        }
        
    } catch (Exception e) {
       Logger.getLogger(logindao.class.getName()).log(Level.SEVERE,null,e);
        return null;
    } finally {
        mysql.CloseConnection(conn);
    }
}
}