/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cafecraft_group2_37a;
import controller.Logincontroller;
import database.*;
import view.*;

 /*
 * @author ASUS
 */
public class Cafecraft_group2_37A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO cDatabDatabase db= new Mysqlconnection();

        Database db = new Mysqlconnection();
        if(db.openConnection()!=null){
            System.out.println("Database connected sucessfuly!");
        }
        else{
            System.out.println("faiiled to connect to database");
        }

  javax.swing.SwingUtilities.invokeLater(() -> {
    login frame = new login();  // Your main window class name
    Logincontroller logincontrol = new Logincontroller(frame);
    frame.setVisible(true);
});
    }
}
    
    
   