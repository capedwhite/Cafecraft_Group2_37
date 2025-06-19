package controller;

import dao.InventoryDAO;
import java.awt.Color;
import java.awt.Dimension;
import model.InventoryItem;
import view.reusableinventory;
import view.inventory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdditemInventoryController {
    private final reusableinventory inputView;
    private final InventoryDAO dao;
    private final inventory mainView;

    public AdditemInventoryController(reusableinventory inputView, inventory mainView) {
        this.inputView = inputView;
        this.dao = new InventoryDAO();
        this.mainView = mainView;

        // === Attach listeners using getter methods ===
        inputView.getQuantitySpinner().addChangeListener(new QuantityChangeListener());

        inputView.getSaveButton().addActionListener(e -> saveItem());
        inputView.getCancelButton().addActionListener(e -> inputView.dispose());
    }

   
    private class QuantityChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int qty = inputView.getQuantity();
            String status = InventoryItem.calculateStatus(qty);
            inputView.setStatus(status);
        }
    }

    
    private void saveItem() {
        String name = inputView.getItemName();
        int quantity = inputView.getQuantity();
        double price = inputView.getPrice();

        
        if (name == null || name.trim().isEmpty()) {
            inputView.showMessage("Item name is required.");
            return;
        }

        if (price < 0) {
            inputView.showMessage("Price must be non-negative.");
            return;
        }

        
        InventoryItem item = new InventoryItem(name, quantity, price);
        dao.addItem(item);

     
mainView.getCardHolderPanel().add(Box.createVerticalStrut(10));
reusableinventory card = new reusableinventory(); 
card.setData(item);
card.setPreferredSize(new Dimension(220, 200));
card.setBorder(BorderFactory.createLineBorder(Color.BLUE));

mainView.getCardHolderPanel().add(card);
mainView.getCardHolderPanel().revalidate();
mainView.getCardHolderPanel().repaint();



        
        
       
        inputView.dispose();

        System.out.println("Item saved and displayed.");
    }
}
