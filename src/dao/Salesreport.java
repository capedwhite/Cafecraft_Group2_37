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
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author ASUS
 */
public class Salesreport {
    Mysqlconnection mysql= new Mysqlconnection();
    public int showorderreport(){
        Connection conn = mysql.openConnection();
        String sql1 = "SELECT COUNT(*) AS total_orders FROM orders WHERE DATE(order_date) = CURDATE()";
         int totalOrders=0;
        try(PreparedStatement pstmt=conn.prepareStatement(sql1)){
            ResultSet rs = pstmt.executeQuery();
           
               if (rs.next()) {
            totalOrders = rs.getInt(1);
        }
        }
      catch (SQLException ex) {
         Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        finally{
                mysql.CloseConnection(conn);}
        return totalOrders;
    }
        
        public double showtotalsales(){
            Connection conn = mysql.openConnection();
               String sql2="SELECT SUM(total_price) FROM orders WHERE DATE(order_date) = CURDATE()";
               double totalSales = 0;
               try(PreparedStatement pstm=conn.prepareStatement(sql2)){
                   ResultSet rs = pstm.executeQuery();
                   
                   if(rs.next()){
                       totalSales = rs.getDouble(1);
                   }
                   
               }
             catch (SQLException ex) {
         Logger.getLogger(Salesreport.class.getName()).log(Level.SEVERE,null,ex);
        }
                finally{
                mysql.CloseConnection(conn);}
                return totalSales;
               
        }
        
}
    
