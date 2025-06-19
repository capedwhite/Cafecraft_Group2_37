package model;

public class InventoryItem {
    private int id; 
    private String itemName;
    private int quantity;
    private double price;
    private String status;

    // Constructors
    public InventoryItem(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.status = calculateStatus(quantity);
    }

    public InventoryItem(int id, String itemName, int quantity, double price) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.status = calculateStatus(quantity);
    }

    // Method to update quantity and recalculate status
    public void updateQuantity(int newQty) {
        this.quantity = newQty;
        this.status = calculateStatus(newQty);
    }

    // Static method to calculate status
    public static String calculateStatus(int quantity) {
        if (quantity == 0) return "Out of Stock";
        else if (quantity <= 10) return "Low Stock";
        else return "In Stock";
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.status = calculateStatus(quantity); // Recalculate status
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
