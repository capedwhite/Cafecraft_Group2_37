/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public interface Database {   
    Connection openConnection();
    void CloseConnection(Connection conn);
    ResultSet runQuery(Connection conn,String Query);
    int executeUpdate(Connection conn,String Query);
}
