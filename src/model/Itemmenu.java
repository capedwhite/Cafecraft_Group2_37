/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Itemmenu {
     private int id;
    private String name;
    private double price;
    private byte[] imagePath;
    private String category;

    public Itemmenu() {
    }

    public Itemmenu(String name, double price, byte[] imagePath, String category) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getImagePath() {
        return imagePath;
    }
    public void setPrice(double price){
        this.price=price;
    }
    
      public void setName(String name) {
     this.name= name;
    }
    public void setImagePath(byte[] imagePath) {
        this.imagePath = imagePath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
public Itemmenu(int id, String name, double price, byte[] imagePath, String category) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.imagePath = imagePath;
    this.category = category;
}
}