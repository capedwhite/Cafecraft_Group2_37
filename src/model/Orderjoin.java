/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;


public class Orderjoin {
      private int orderId;
    private int userId;
    private String username;
    private Date orderDate;
    private List<Orderentry> orderDetails;
    private String status;
public Orderjoin(int orderID,int userID,String username,Date orderDate,List<Orderentry>orderDetails){
    this.orderId=orderID;
    this.userId=userID;
    this.username = username;
    this.orderDate=orderDate;
    this.orderDetails=orderDetails;
}
public Orderjoin(int orderID,int userID,String username,Date orderDate,List<Orderentry>orderDetails,String status){
    this.orderId=orderID;
    this.userId=userID;
    this.username = username;
    this.orderDate=orderDate;
    this.orderDetails=orderDetails;
    this.status=status;
}
public int getOrderId() {
        return orderId;
    }
public String getstatus(){
    return status;
}
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Orderentry> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<Orderentry> orderDetails) {
        this.orderDetails = orderDetails;
    }
}

