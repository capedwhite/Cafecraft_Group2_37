/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Itemmenu;
import view.adddialouge;
import view.editmenu;

/**
 *
 * @author ASUS
 */
public class AddItemController {
    private final adddialouge userdialouge;
    
    private final view.editmenu editMenu;
    private final Itemdao itemdao = new Itemdao();
     private byte[] imageBytes = null; 
     private final ItemController itemcontroller;
    public AddItemController(adddialouge userdialouge,editmenu editMenu,ItemController itemcontroller){
        this.userdialouge=userdialouge;
        this.editMenu=editMenu;
        this.itemcontroller = itemcontroller;
        userdialouge.addSaveitembuttonlistener(new savebuttonlistener());
        userdialouge.addChooseimagelistener(new chooseimagelistener());
    }
    class savebuttonlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            System.out.println("button clicked");
            String itemname=userdialouge.getitemField().getText();
            String category=(String) userdialouge.getcategoryfield().getSelectedItem();
            String price=userdialouge.getpricefield().getText();
            if (itemname.isEmpty() || category.isEmpty() || price.isEmpty() || imageBytes == null) {
                System.out.println("Please fill all fields and select an image!");
                return;
            }
            double pricetext = Double.parseDouble(price); 
            Itemmenu item = new Itemmenu(itemname,pricetext,imageBytes,category);
            
                boolean check = itemdao.Insertitem(item);
                if(check){
                JOptionPane.showMessageDialog(userdialouge,"menu item added sucessfully");
                
                itemcontroller.loadAllItemsToPanel();
            }  
                else{
                    JOptionPane.showMessageDialog(userdialouge,"failed to add item");
                }
    }
            
         catch(Exception ex){
            System.out.println("error adding item" + ex.getMessage());
        }
         
    }
    }
class chooseimagelistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            

  try{
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION) {
      File imageFile = fileChooser.getSelectedFile(); 
      BufferedImage img = ImageIO.read(imageFile);
      userdialouge.setImagePreview(img);
          if (img!= null) {
        try {
            imageBytes = Files.readAllBytes(imageFile.toPath()); 
        } catch (Exception ex) {
            System.out.println("error adding item" + ex.getMessage());
      }

    }
      }
  }
  catch (IOException ex) {
                Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
  }
    }
  
    
