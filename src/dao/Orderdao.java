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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Orderentry;
import model.Orderjoin;


/**
 *
 * @author ASUS
 */
public class Orderdao {
    int orderid;
    Mysqlconnection mysql= new Mysqlconnection();
     public boolean Insertorderdetails(List<Orderentry>orderentries,int user_id, double totalPrice){
      Connection conn=mysql.openConnection();
       try{   
            
             String orderqr = "INSERT INTO Orders (user_id, total_price) VALUES (?, ?)";
                try(PreparedStatement pstmt=conn.prepareStatement(orderqr,PreparedStatement.RETURN_GENERATED_KEYS)){
pstmt.setInt(1,user_id);
pstmt.setDouble(2,totalPrice);
int affectedrows=pstmt.executeUpdate();

        if (affectedrows > 0) {
            ResultSet rs = pstmt.getGeneratedKeys(); 
            if (rs.next()) {
             orderid= rs.getInt(1); 
            }
        }
        

}
         String orderdetailqr="INSERT INTO OrderDetails(order_id,item_id, quantity, price,itemname) VALUES (?, ?, ?, ?, ?)";
                try(PreparedStatement pstm =conn.prepareStatement(orderdetailqr) ){
                    for(Orderentry orderentry : orderentries){
                    pstm.setInt(1,orderid);
                    pstm.setInt(2,orderentry.getItemId());
                    pstm.setInt(3,orderentry.getQty());
                    pstm.setDouble(4,orderentry.getUnitPrice());
                    pstm.setString(5,orderentry.getProductName());
                    int affectedrows=pstm.executeUpdate();
                  
                }
                    
                    return true;
       }
                  
       }
    catch(SQLException ex){
Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
return false;
}
          finally{
mysql.CloseConnection(conn);
}
     }     

     public List<Orderjoin> getAllPendingOrders() {
    List<Orderjoin> orders = new ArrayList<>();
    try (Connection con = mysql.openConnection()) {
        String sql = "SELECT o.order_id, o.user_id, u.username, o.order_date, " +
                 "od.itemname, od.quantity, od.price " +
                 "FROM orders o " +
                 "INNER JOIN orderdetails od ON o.order_id = od.order_id " +
                 "INNER JOIN users u ON o.user_Id = u.id " +
                 "WHERE o.Status = 'Pending' " +
                 "ORDER BY o.Order_id";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        Map<Integer, Orderjoin> orderMap = new LinkedHashMap<>();
        
        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            int userId = rs.getInt("user_id");
            String username = rs.getString("username");
            Date orderDate = rs.getDate("order_date");
            
            String itemName = rs.getString("itemname");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            Orderentry entry = new Orderentry(itemName,price,quantity);
            
            Orderjoin order = orderMap.get(orderId);
            if (order == null) {
                List<Orderentry> itemList = new ArrayList<>();
                itemList.add(entry);
                order = new Orderjoin(orderId, userId, username, orderDate, itemList);
                orderMap.put(orderId, order);
            } else {
                order.getOrderDetails().add(entry);
            }
        }
            orders.addAll(orderMap.values());
        }
    
     
catch (Exception e) {
        e.printStackTrace();
    }
    return orders;
}
     


     

public boolean updatestatus(int orderId) {
    String sql = "UPDATE orders SET status = 'Completed' WHERE order_id = ?";
    Connection con = mysql.openConnection();
        try(PreparedStatement psmt = con.prepareStatement(sql)) {

        psmt.setInt(1, orderId);
        int rowsUpdated = psmt.executeUpdate();
        
        return rowsUpdated > 0; 

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
 public List<Orderjoin> getAllorderhistory(int user_id){
    List<Orderjoin> orders = new ArrayList<>();
    try (Connection con = mysql.openConnection()) {
        String sql = "SELECT o.order_id, o.user_id, u.username, o.order_date,o.Status," +
                     "od.itemname, od.quantity, od.price " +
                     "FROM orders o " +
                     "INNER JOIN orderdetails od ON o.order_id = od.order_id " +
                     "INNER JOIN users u ON o.user_id = u.id " +
                     "WHERE o.user_id = ? " +
                     "ORDER BY o.order_id DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,user_id);
        ResultSet rs = ps.executeQuery();

        Map<Integer, Orderjoin> orderMap = new LinkedHashMap<>();
        
        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            String username = rs.getString("username");
            Date orderDate = rs.getDate("order_date");
            
            String itemName = rs.getString("itemname");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            String status = rs.getString("Status");
            Orderentry entry = new Orderentry(itemName,price,quantity);
            
            Orderjoin order = orderMap.get(orderId);
            if (order == null) {
                List<Orderentry> itemList = new ArrayList<>();
                itemList.add(entry);
                order = new Orderjoin(orderId, user_id, username, orderDate, itemList,status);
                orderMap.put(orderId, order);
            } else {
                order.getOrderDetails().add(entry);
            }
        }
            orders.addAll(orderMap.values());
        }
    
     
catch (Exception e) {
        e.printStackTrace();
    }
    return orders;
}
}