/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cafecraft_group2_37a;
import controller.Signupcontroller;
import database.*;
import view.Signup;


/**
 *
 * @author ASUS
 */
public class Cafecraft_group2_37A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database db= new Mysqlconnection();
        if(db.openConnection()!=null){
            System.out.println("Database connected sucessfuly!");
        }
        else{
            System.out.println("faiiled to connect to database");
        }
    
    
    Signup signupview = new Signup();
    Signupcontroller controller = new Signupcontroller (signupview);
    
    controller.open();
    
}
}
