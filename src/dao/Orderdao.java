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
import model.Orderentry;


/**
 *
 * @author ASUS
 */
public class Orderdao {
    int orderid;
    Mysqlconnection mysql= new Mysqlconnection();
     public boolean Insertorderdetails(Orderentry orderentry,int user_id, double totalPrice){
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
                    pstm.setInt(1,orderid);
                    pstm.setInt(2,orderentry.getItemId());
                    pstm.setInt(3,orderentry.getQty());
                    pstm.setDouble(4,orderentry.getUnitPrice());
                    pstm.setString(5,orderentry.getProductName());
                    int affectedrows=pstm.executeUpdate();
                    return affectedrows >0;
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

     }

