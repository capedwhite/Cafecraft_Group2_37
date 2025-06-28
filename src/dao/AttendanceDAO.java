/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Mysqlconnection;
import java.util.List;
import model.Attendancemodel;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
/**
 *
 * @author ASUS
 */
public class AttendanceDAO {
    Mysqlconnection mysql = new Mysqlconnection();
    
    public boolean insertMultipleAttendances(List<Attendancemodel> attendancelist) {
     Connection con=  mysql.openConnection();
    String sql = "INSERT INTO attendance (worker_id, status, attendance_date) VALUES (?, ?, ?)";

    try (PreparedStatement pst = con.prepareStatement(sql)) {
        for (Attendancemodel a : attendancelist) {
            pst.setInt(1, a.getWorkerId());
            pst.setString(2, a.getStatus());
            pst.setDate(3,  new java.sql.Date(a.getAttendanceDate().getTime()));
            pst.addBatch(); // batch each insert
        }
        pst.executeBatch();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    finally{
        mysql.CloseConnection(con);
    }
}
    public int getTotalByStatus(String status,Date date) {
         Connection con=  mysql.openConnection();
        int total = 0;
        String sql = "SELECT COUNT(*) FROM attendance WHERE status = ? and attendance_date=?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, status);
            pst.setDate(2,new java.sql.Date(date.getTime()));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
finally{
            mysql.CloseConnection(con);
        }
        return total;
    }
}
    
    

