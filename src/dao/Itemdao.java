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
return    affectedrows != 0;
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
        String sql = "SELECT id, name, price, image_path, Category FROM items where is_active= 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                Itemmenu item = new Itemmenu(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getBytes("image_path"),
                    rs.getString("category")
                );
                items.add(item);
            }
        } catch (SQLException ex) {
         Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        }
                  finally{
mysql.CloseConnection(conn);
}
        return items;
    }

      
public boolean updateItems(Itemmenu item){
    Connection conn=mysql.openConnection();
    String sql = "UPDATE items SET name = ?, price = ?, category = ?, image_path = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getCategory());
            stmt.setBytes(4, item.getImagePath());
            stmt.setInt(5, item.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
            return false;
        }
              finally{
mysql.CloseConnection(conn);
}
}
public boolean deleteItemById(Itemmenu item) {
    String sql = "UPDATE items SET is_active = 0 WHERE id = ?";
    Connection conn=mysql.openConnection();
         try(PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, item.getId());
        int rows = stmt.executeUpdate();
        return rows > 0;

    } catch (SQLException ex) {
        Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        return false;
    }
}

public List<Itemmenu> getItemsByCategory(String category) {
    List<Itemmenu> itemList = new ArrayList<>();
 Connection conn=mysql.openConnection();
 String sql = "SELECT * FROM items WHERE category = ?";
    try {PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, category);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String cat = rs.getString("category");
            double price = rs.getDouble("price");
            byte[] imageBytes = rs.getBytes("image_path");
            itemList.add(new Itemmenu(id,name,price,imageBytes,cat));
        }

        rs.close();
        stmt.close();
        conn.close();

    } 
    catch (Exception e) {
        e.printStackTrace();
    }

    return itemList;
}

}


