package dao;

import database.Database;
import database.Mysqlconnection;
import model.InventoryItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    private final Database db;

    public InventoryDAO() {
        db = new Mysqlconnection();
    }

    
    public void addItem(InventoryItem item) {
        String sql = "INSERT INTO inventory (item_name, quantity, price, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, item.getItemName());
            pst.setInt(2, item.getQuantity());
            pst.setDouble(3, item.getPrice());
            pst.setString(4, item.getStatus());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Item inserted successfully.");
                // Optional: retrieve generated ID
                try (ResultSet keys = pst.getGeneratedKeys()) {
                    if (keys.next()) {
                        int generatedId = keys.getInt(1);
                        System.out.println("New item ID: " + generatedId);
                    }
                }
            } else {
                System.out.println("⚠️ Insert failed: No rows affected.");
            }

        } catch (SQLException e) {
            System.out.println(" Error inserting item: " + e.getMessage());
        }
    }

    
    public void updateQuantityAndStatus(int id, int newQuantity) {
        String newStatus = InventoryItem.calculateStatus(newQuantity);
        String sql = "UPDATE inventory SET quantity = ?, status = ? WHERE id = ?";

        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, newQuantity);
            pst.setString(2, newStatus);
            pst.setInt(3, id);
            pst.executeUpdate();

            System.out.println(" Item updated successfully.");
        } catch (SQLException e) {
            System.out.println(" Error updating item: " + e.getMessage());
        }
    }

    
    public List<InventoryItem> getAllItems() {
        List<InventoryItem> itemList = new ArrayList<>();
        String sql = "SELECT * FROM inventory";

        try (Connection conn = db.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InventoryItem item = new InventoryItem(
                        rs.getInt("id"),
                        rs.getString("item_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                itemList.add(item);
            }

            System.out.println(" Fetched " + itemList.size() + " items from database.");

        } catch (SQLException e) {
            System.out.println(" Error fetching items: " + e.getMessage());
        }

        return itemList;
    }

    
    public void deleteItem(int id) {
        String sql = "DELETE FROM inventory WHERE id = ?";

        try (Connection conn = db.openConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println(" Item deleted successfully.");
        } catch (SQLException e) {
            System.out.println(" Error deleting item: " + e.getMessage());
        }
    }
}
