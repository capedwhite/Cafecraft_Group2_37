/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Itemdao;
import java.util.List;
import javax.swing.JPanel;
import model.Itemmenu;
import view.MenuItems;
import view.useritempanel;

/**
 *
 * @author ASUS
 */
public class UserMenucontroller {
    private final Itemdao itemdao;
    private final MenuItems menuitem;
    
    public UserMenucontroller(Itemdao itemdao,MenuItems menuitem){
        this.itemdao=itemdao;
        this.menuitem=menuitem;
        loadUserItems();
        
        
    }
    public void loadUserItems() {
 List<Itemmenu> itemList = itemdao.getAllMenuItems(); 
        JPanel menuPanel = menuitem.getMenupanel(); 

        menuPanel.removeAll();
        for (Itemmenu item : itemList) {
           useritempanel card = menuitem.addItemCard(item); 
            Useritempanelcontroller userpanelcontrol = new Useritempanelcontroller();
        }

        menuPanel.revalidate(); //update ui
        menuPanel.repaint();
    }
}

