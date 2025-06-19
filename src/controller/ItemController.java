/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.editmenu;
import dao.Itemdao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Itemmenu;
import view.adddialouge;
import view.admin_sidebar;
import view.reusemenupanel;

/**
 *
 * @author ASUS
 */
public final class ItemController {

    private final Itemdao itemDao =new Itemdao();
    private final editmenu userView;
   
   
    
    public ItemController (editmenu userView){
        this.userView = userView;
        
        userView.addAddItemListener(new AddItemListener());
        userView.addAddresetitemListener(new resetlistener());
        userView.addAddexitbtnlistener(new Exitlistener());
        loadAllItemsToPanel();
        
    };
    


    public void loadAllItemsToPanel() {
        List<Itemmenu> itemList = itemDao.getAllMenuItems(); 
        JPanel menuPanel = userView.getmenupanel(); 

        menuPanel.removeAll();
        for (Itemmenu item : itemList) {
           reusemenupanel card = userView.addItemCard(item); 
            ReusablePanelController Reusecontrol = new ReusablePanelController(card,itemDao,userView,ItemController.this);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }
    
    

    class AddItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           
               adddialouge dialog = new adddialouge(userView,true);
               AddItemController additemcontrol = new AddItemController(dialog,userView,ItemController.this);// your custom dialog to input details   
    dialog.setVisible(true);

        }
        
    }
    class resetlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the menu?", "Confirm Reset", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        boolean deleted = new Itemdao().deleteAllItems();
        if (deleted) {
            userView.removeAll();
           userView.revalidate();
            userView.repaint();
            JOptionPane.showMessageDialog(null, "Menu has been reset!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to reset menu.");
        }
    }
        }
        
    }
    class Exitlistener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            userView.dispose();
            admin_sidebar adminside = new admin_sidebar();
            admindasboardcontroller admindashboard = new admindasboardcontroller(adminside);
            adminside.setVisible(true);
            
        }
        
    }
}
        
    
