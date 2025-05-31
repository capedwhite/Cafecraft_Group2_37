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
import view.editdialoge;
import view.editmenu;


/**
 *
 * @author ASUS
 */
public class Edititemcontroller {
    private final editdialoge userdialoge;
    private final Itemdao itemdao;
    private final Itemmenu item;
    private final editmenu userView;
    public Edititemcontroller(editdialoge userdialoge,Itemdao itemdao,Itemmenu item,editmenu userView){
        this.userdialoge=userdialoge;
        this.itemdao=itemdao;
        this.item=item;
        this.userView=userView;
        userdialoge.setFields(item);
        userdialoge.savebuttonlistener(new savebutton());
        userdialoge.addChooseimagelistener(new chooseimage());
    }
    class savebutton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
        
           item.setName(userdialoge.getitemField().getText());
            item.setPrice(Double.parseDouble(userdialoge.getpricefield().getText()));
            item.setCategory(userdialoge.getcategoryfield().getText());
            
            boolean check=itemdao.updateItems(item);
            if(check){
                 JOptionPane.showMessageDialog(userdialoge,"edited item sucessfully");
                 
                 ItemController itemcontrol = new ItemController(userView);
                 itemcontrol.loadAllItemsToPanel();
            }
            else{
                JOptionPane.showMessageDialog(userdialoge,"failed to edit item");
            }
          
        }
            catch(Exception ex){
            System.out.println("error adding item" + ex.getMessage());
                
            }
        
    }
    }
    class chooseimage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION) {
      File imageFile = fileChooser.getSelectedFile(); 
      BufferedImage img = ImageIO.read(imageFile);
      userdialoge.setimagefield(img);
          if (img!= null) {
        try {
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath()); 
            item.setImagePath(imageBytes);
        } catch (IOException ex) {
           
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
    
    

