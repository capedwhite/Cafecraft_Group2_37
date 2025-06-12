/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Employee {
    private int id;
    private String employee_name;
    private String employee_Status;
    private String employee_number;
    
    public Employee(){
    }
    public Employee(int id,String employee_name,String employee_Status,String employee_number){
        this.id=id;
        this.employee_name=employee_name;
        this. employee_number = employee_number;
        this.employee_Status=employee_Status;
     
    }
        public Employee(String employee_name,String employee_Status,String employee_number){
       
        this.employee_name=employee_name;
        this. employee_number = employee_number;
        this.employee_Status=employee_Status;
     
    }
     public int getId() {
        return id;
    }
    public String getName() {
        return employee_name;
    }

    public String getNumber() {
        return employee_number ;
    }

    public String getstatus() {
        return employee_Status;
    }
    public void setName(String employee_name){
        this.employee_name=employee_name;
    }
    
 public void setStatus(String employee_Status){
        this.employee_Status=employee_Status;
    }
 public void setNumber(String employee_number){
     this.employee_number=employee_number;
 }
    
    
}
