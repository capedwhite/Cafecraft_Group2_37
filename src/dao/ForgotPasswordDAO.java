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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Unish K.C
 */
public class ForgotPasswordDAO {
      Mysqlconnection mysql = new Mysqlconnection();
    public void updatePassword(User user) {
        try {
            Connection con = mysql.openConnection();

            String sql = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getUsername());

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Username not found.");
            }

            mysql.CloseConnection(con);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
public String getSecurityQuestion(String username) {
        try {
            Connection con = mysql.openConnection();
            String sql = "SELECT security_question FROM users WHERE username = ?";
             PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, username);
            ResultSet rs = psmt.executeQuery();
             if (rs.next()) {
                return rs.getString("security_question");
            }
        }
             catch (SQLException e) {
          Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }

public boolean checkSecurityAnswer(String username, String answer) {
        try{
             Connection conn = mysql.openConnection();
              String sql = "SELECT * FROM users WHERE username = ? AND security_answer = ?";
                 PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, username);
            psmt.setString(2, answer);
            ResultSet rs = psmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
              Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,e);
        }
        return false;
    }

}




