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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;


/**
 *
 * @author ASUS
 */
public class Employeedao {
     Mysqlconnection mysql= new Mysqlconnection();
      
      public boolean InsertEmployee(Employee employee){
      Connection conn=mysql.openConnection();
          
          String sql = "INSERT INTO employees(employee_name,employee_Status,employee_number)VALUES(?,?,?)";
          try(PreparedStatement pstmt=conn.prepareStatement(sql)){
pstmt.setString(1,employee.getName());
pstmt.setString(2,employee.getstatus());
pstmt.setInt(3,employee.getNumber());
int affectedrows=pstmt.executeUpdate();
return    affectedrows != 0;
}
    catch(SQLException ex){
Logger.getLogger(Employeedao.class.getName()).log(Level.SEVERE,null,ex);
return false;
}
          finally{
mysql.CloseConnection(conn);
}
}
           public List<Employee> getAllworker() {
             Connection conn=mysql.openConnection();
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id,employee_name, employee_Status,employee_number from employees";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while(rs.next()) {
                Employee employee= new Employee(
                    rs.getInt("id"),
                    rs.getString("employee_name"),
                    rs.getString("employee_Status"),
                    rs.getInt("employee_number")
                        
                );
                employees.add(employee);
            }
        } catch (SQLException ex) {
         Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        }
                  finally{
mysql.CloseConnection(conn);
}
        return employees;
    }
           
           public boolean UpdateItems(Employee employee){
               Connection conn=mysql.openConnection();
    String sql = "UPDATE employees SET employee_name = ?, employee_status = ?, employee_number=? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getstatus());
            stmt.setInt(3, employee.getNumber());
            stmt.setInt(4,employee.getId());

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
           public boolean deleteItemById(Employee employee) {
    String sql = "DELETE FROM employees WHERE id = ?";
    Connection conn=mysql.openConnection();
         try(PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, employee.getId());
        int rows = stmt.executeUpdate();
        return rows > 0;

    } catch (SQLException ex) {
        Logger.getLogger(Itemdao.class.getName()).log(Level.SEVERE,null,ex);
        return false;
    }
}
}
