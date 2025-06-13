/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.editdialoge;
import view.reusemenupanel;


/**
 *
 * @author ASUS
 */
import model.Itemmenu;
import view.editmenu;
public class ReusablePanelController {
    private final reusemenupanel userreuse;
    private final Itemdao itemdao;
    private final editmenu userView;
    private final ItemController itemcontroller;
    
    
    public ReusablePanelController(reusemenupanel userreuse,Itemdao itemdao,editmenu userView,ItemController itemcontroller){
        this.userreuse = userreuse;
        this.itemdao=itemdao;
        this.userView= userView;
        this.itemcontroller = itemcontroller;
        userreuse.addADDeditblistener(new EditListener());
        userreuse.addADDdeletelistener(new Deletelistener());
        
        
    }
        
            
            
    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button clicked");
            Itemmenu selectedItem = userreuse.getItem();
            editdialoge editpanel = new editdialoge();
            Edititemcontroller editController = new Edititemcontroller(editpanel,itemdao, selectedItem,userView,itemcontroller);
            editpanel.setVisible(true);
        }
        
        
    }
    class Deletelistener implements ActionListener{

        @Override
        
        public void actionPerformed(ActionEvent e) {
            try{
             System.out.println("button clicked");
             Itemmenu selectedItem = userreuse.getItem();
             boolean check = itemdao.deleteItemById(selectedItem);
             if(check){
                 JOptionPane.showMessageDialog(userView,"Deleted item sucessfully");
                ItemController itemcontrol = new ItemController(userView);
                itemcontrol.loadAllItemsToPanel();
                 
             }
             else{
                 JOptionPane.showMessageDialog(userreuse,"couldnt delete files");
             }
             
        }
            catch(Exception ex){
            System.out.println("error adding item" + ex.getMessage());
                 }
}
    }
}
