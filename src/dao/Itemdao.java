/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import model.Itemmenu;
import java.sql.ResultSet;
/**
 *
 * @author ASUS
 */
public class Itemdao {
      Mysqlconnection mysql= new Mysqlconnection();
      
      public boolean Insertitem(Itemmenu item){
      Connection conn=mysql.openConnection();
          
          String sql = "INSERT INTO items(name,price,image_path,Category)VALUES(?,?,?,?)";
          try(PreparedStatement pstmt=conn.prepareStatement(sql)){
pstmt.setString(1,item.getName());
pstmt.setDouble(2,item.getPrice());
pstmt.setBytes(3,item.getImagePath());
pstmt.setString(4,item.getCategory());
int affectedrows=pstmt.executeUpdate();
if(affectedrows == 0){
    return false;
}
return true;
}
    catch(SQLException ex){
Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
return false;
}
          finally{
mysql.CloseConnection(conn);
}
}
       public List<Itemmenu> getAllMenuItems() {
             Connection conn=mysql.openConnection();
        List<Itemmenu> items = new ArrayList<>();
        String sql = "SELECT id, name, price, image_path, Category FROM menu_items";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                Itemmenu item = new Itemmenu(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getBytes("imagePath"),
                    rs.getString("category")
                );
                items.add(item);
            }
        } catch (SQLException ex) {
         Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return items;
    }

      
 }

