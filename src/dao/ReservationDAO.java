package dao;

import database.Mysqlconnection;
import model.Reservation;
import java.sql.*;
import java.util.*;

public class ReservationDAO {

    Mysqlconnection db = new Mysqlconnection(); // ✅ use your structure

    // ✅ Save new reservation (user side)
    public boolean saveReservation(Reservation r) {
        String sql = "INSERT INTO reservations (username, no_of_people, date, time, table_no, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = db.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getUsername());
            ps.setInt(2, r.getNoOfPeople());
ps.setString(3, r.getDate());
            ps.setString(4, r.getTime());
            ps.setInt(5, r.getTableNo());
            ps.setString(6, r.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ✅ Get available tables (user side)
    public List<Integer> getAvailableTables(int totalTables) {
        List<Integer> available = new ArrayList<>();
        for (int i = 1; i <= totalTables; i++) {
            available.add(i);
        }

        String sql = "SELECT table_no FROM reservations WHERE status = 'pending'";
        try (Connection con = db.openConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int t = rs.getInt("table_no");
                available.remove((Integer) t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    // ✅ Get all reservations (admin side)
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection con = db.openConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation(
                    rs.getString("username"),
                    rs.getInt("no_of_people"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getInt("table_no")
                );
                r.setStatus(rs.getString("status"));
                list.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ✅ Mark reservation as complete (admin side)
    public void markReservationComplete(int tableNo) {
        String sql = "UPDATE reservations SET status = 'complete' WHERE table_no = ? AND status = 'pending'";

        try (Connection con = db.openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tableNo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ✅ added by Prajal - clear all reservations from database
public boolean deleteAllReservations() {
    String sql = "DELETE FROM reservations";
    try (Connection con = new Mysqlconnection().openConnection();
         Statement stmt = con.createStatement()) {
        stmt.executeUpdate(sql);
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}
