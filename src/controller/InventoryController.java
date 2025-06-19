package controller;

import dao.InventoryDAO;
import model.InventoryItem;
import view.inventory;
import view.reusableinventory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InventoryController {
    private final inventory view;
    private final InventoryDAO dao;

    public InventoryController(inventory view) {
        this.view = view;
        this.dao = new InventoryDAO();

        this.view.getAddItemButton().addActionListener(e -> showAddPanel());
        this.view.getExitButton().addActionListener(e -> System.exit(0));

        loadAllItems();
    }

    private void loadAllItems() {
        List<InventoryItem> items = dao.getAllItems();
        for (InventoryItem item : items) {
            addInventoryCard(item);
        }
    }

    private void showAddPanel() {
        reusableinventory inputPanel = new reusableinventory(true);
        inputPanel.setPreferredSize(new Dimension(400, 250));

        JDialog dialog = new JDialog();
        dialog.setTitle("Add Inventory Item");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setContentPane(inputPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        inputPanel.getQuantitySpinner().addChangeListener(e -> {
            int qty = inputPanel.getQuantity();
            inputPanel.setStatus(InventoryItem.calculateStatus(qty));
        });

        inputPanel.getSaveButton().addActionListener(e -> {
            String name = inputPanel.getItemName();
            int quantity = inputPanel.getQuantity();
            double price = inputPanel.getPrice();

            if (name == null || name.trim().isEmpty()) {
                inputPanel.showMessage("Item name is required.");
                return;
            }

            if (price < 0) {
                inputPanel.showMessage("Price must be non-negative.");
                return;
            }

            InventoryItem item = new InventoryItem(name, quantity, price);
            dao.addItem(item);
            addInventoryCard(item);
            dialog.dispose();
        });

        inputPanel.getCancelButton().addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private void addInventoryCard(InventoryItem item) {
        reusableinventory card = new reusableinventory();
        card.setData(item);
        card.setPreferredSize(new Dimension(220, 200));
        card.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        applyStatusColor(card, item.getStatus());

        card.addQuantityChangeListener(e -> {
            int newQty = card.getQuantity();
            String newStatus = InventoryItem.calculateStatus(newQty);
            card.setStatus(newStatus);
            applyStatusColor(card, newStatus);
            dao.updateQuantityAndStatus(item.getId(), newQty);
        });

        view.getCardHolderPanel().add(card);
        view.getCardHolderPanel().revalidate();
        view.getCardHolderPanel().repaint();
    }

    private void applyStatusColor(reusableinventory card, String status) {
        if (status.equalsIgnoreCase("out of stock")) {
            card.getStatusLabel().setForeground(new Color(220, 53, 69));
        } else if (status.equalsIgnoreCase("low stock")) {
            card.getStatusLabel().setForeground(new Color(74, 144, 226));
        } else if (status.equalsIgnoreCase("in stock")) {
            card.getStatusLabel().setForeground(new Color(40, 167, 69));
        } else {
            card.getStatusLabel().setForeground(Color.BLACK);
        }
    }
}
