/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
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
    public static boolean validateUser(User user) {
       Connection conn = mysql.openConnection();
        boolean isValid = false;
        
        try {
            Connection con = Connectionprovider.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();

            isValid = rs.next();

            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
     Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,ex);
        }

        return isValid;
    }
}

}
