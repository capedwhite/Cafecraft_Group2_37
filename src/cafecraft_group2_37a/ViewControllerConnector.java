/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafecraft_group2_37a;

import controller.MenuitemsController;
import zview.MenuItems;

/**
 c 
 * @author Acer
 */
public class ViewControllerConnector {
    public static void main(String[] args) {
        // Create the view
        MenuItems view = new MenuItems();
        
        // Connect the view to the controller
        new MenuitemsController(view);

        // Show the GUI
        view.setVisible(true);
    }
}
