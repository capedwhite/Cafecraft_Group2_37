package dao;

import database.Mysqlconnection;
import model.AboutCafe;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AboutCafeDAO {
Mysqlconnection mysql= new Mysqlconnection();
    public boolean insertAboutText(AboutCafe aboutcafe) {
        Connection conn = mysql.openConnection();
            String query = "INSERT INTO about_cafe(content)VALUES(?)";
try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setString(1, aboutcafe.getContent());
            int affectedrows=ps.executeUpdate();
            return    affectedrows != 0;
        } catch (Exception ex) {
            Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
 finally{
mysql.CloseConnection(conn);
}
    }


    public boolean updateAboutText(String newText) {
        try (Connection con = Mysqlconnection.getConnection()) {
            String updateQuery = "UPDATE about_cafe SET content = ? WHERE id = 1";
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.setString(1, newText);
            int rows = ps.executeUpdate();

            // If no rows, insert
            if (rows == 0) {
                String insertQuery = "INSERT INTO about_cafe (id, content) VALUES (1, ?)";
                PreparedStatement insertPs = con.prepareStatement(insertQuery);
                insertPs.setString(1, newText);
                return insertPs.executeUpdate() > 0;
            }
            return true;

        } catch (Exception ex) {
            Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }

public String getText(int id){
        try {
            Connection con = mysql.openConnection();
            String sql = "SELECT content from about_cafe where id = ?";
             PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setInt(1,id);
            ResultSet rs = psmt.executeQuery();
             if (rs.next()) {
                return rs.getString("content");
            }
        }
             catch (SQLException e) {
          Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,e);
        }
        return null;
    }
}
