/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cafecraft_group2_37a;
import database.*;
import view.*;
import controller.*;
/**
 *
 * @author ASUS
 */
public class Cafecraft_group2_37A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database db = new Mysqlconnection();
        if(db.openConnection()!=null){
            System.out.println("Database connected sucessfuly!");
        }
        else{
            System.out.println("faiiled to connect to database");
        }
         javax.swing.SwingUtilities.invokeLater(() -> {
        editmenu frame = new editmenu();  // Your main window class name
          ItemController controller = new ItemController(frame);
        frame.setVisible(true);
    });
    }
    }
    
    

