/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.editmenu;
import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.adddialouge;
import view.editdialoge;

/**
 *
 * @author ASUS
 */
public class ItemController {
//    String name = nameField.getText();
//String category = categoryCombo.getSelectedItem().toString();
//double price = Double.parseDouble(priceField.getText());
//byte[] imageBytes = getImageBytesFromLabelOrChooser();

    private final Itemdao itemDao =new Itemdao();
    private final editmenu userView;
    
    
    public ItemController (editmenu userView){
        this.userView = userView;
        
        userView.addAddItemListener(new AddItemListener());
        userView.addADDEditlistener(new ADDEditListener());
    }
    public void open(){
        
        this.userView.setVisible(true);
    }
    public void close(){
        this.userView.dispose();
    }
    class AddItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               adddialouge dialog = new adddialouge(userView,true); // your custom dialog to input details
    dialog.setVisible(true);

        }
        
    }
    class ADDEditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               editdialoge dialog = new editdialoge(userView,true); // your custom dialog to input details
    dialog.setVisible(true);

        }
    }
}
        
    
