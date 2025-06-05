package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import view.MenuItems;
import view.useritempanel;

public final class MenuitemsController {

    private final MenuItems view;
    private int x = 0;
    private double total = 0.0;
    private boolean isConfirmed = false;
    private final Map<String, OrderEntry> orderMap = new LinkedHashMap<>();
    private boolean listenersAttached = false;
    private boolean isUpdatingReceipt = false;

    public MenuitemsController(MenuItems view) {
        this.view = view;
        this.view.addbtnResetListener(e -> resetForm());
        this.view.addConfirmListener(e -> handleConfirm());
        addReceiptButtonListener();
        init();
    }

    public void init() {
        if (!listenersAttached) {
            attachCheckboxListeners();
            listenersAttached = true;
        }
        setTime();
    }

    private void handleConfirm() {
        orderMap.clear();
        boolean hasValidItems = false;

        for (useritempanel card : view.getItemCards()) {
            int qty = card.getQuantity();
            String productName = card.getItemName();
            double rate = card.getItemPrice();

            if (card.isSelected() && qty > 0) {
                orderMap.put(productName, new OrderEntry(productName, rate, qty));
                hasValidItems = true;
            }
        }

        if (!hasValidItems) {
            JOptionPane.showMessageDialog(null,
                    "No valid items selected. Please check the quantity and selection.",
                    "Confirmation Failed",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        isConfirmed = true;
        updateReceiptDisplay();
        calculateTotal();

        String username = System.getProperty("user.name");
        JOptionPane.showMessageDialog(null, "Order confirmed! You may now view your receipt . " + username);
    }

    public void CafeCraft() {
        String headers = String.format("%-5s %-20s %-10s %-10s %-10s\n", "No.", "Item", "Qty", "Rate", "Total");
        view.getTxtArea().setText(
                """
                ********************* CafeCraft *********************
                Time: """ + view.getTxtTime().getText() + "    Date: " + view.getTxtDate().getText() + "\n" +
                "**************************************************************\n" +
                headers
        );
    }

    public void attachCheckboxListeners() {
        for (useritempanel card : view.getItemCards()) {
            ActionListener[] existingListeners = card.getCheckbox().getActionListeners();
            for (ActionListener l : existingListeners) {
                card.getCheckbox().removeActionListener(l);
            }

            card.getCheckbox().addActionListener(e -> {
                if (isUpdatingReceipt) return;

                int qty = card.getQuantity();
                String productName = card.getItemName();
                double rate = card.getItemPrice();

                if (card.isSelected() && qty == 0) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Please increase quantity for: suresh" + productName);
                    });
                    card.setSelected(false);
                    return;
                }

                isUpdatingReceipt = true;
                try {
                    if (card.isSelected()) {
                        orderMap.put(productName, new OrderEntry(productName, rate, qty));
                    } else {
                        orderMap.remove(productName);
                    }

                    updateReceiptDisplay();
                    isConfirmed = false;
                } finally {
                    isUpdatingReceipt = false;
                }
            });
        }
    }

    private void updateReceiptDisplay() {
        x = 1;
        total = 0.0;
        CafeCraft();

        for (OrderEntry entry : orderMap.values()) {
            total += entry.total;
            String line = String.format("%-5d %-20s %-10d %-10.2f %-10.2f\n",
                    x++, entry.productName, entry.qty, entry.unitPrice, entry.total);
            view.getTxtArea().append(line);
        }
    }

    private void calculateTotal() {
        double subtotal = total;
        double tax = subtotal * 0.13;
        double finalTotal = subtotal + tax;

        view.getTxtSubTotal().setText(String.format("%.2f", subtotal));
        view.getTxtTax().setText(String.format("%.2f", tax));
        view.getTxtTotal().setText(String.format("%.2f", finalTotal));

        StringBuilder summary = new StringBuilder();
        summary.append("\n**************************************************************\n")
               .append(String.format("%-30s %10.2f\n", "Tax:", tax))
               .append(String.format("%-30s %10.2f\n", "Sub Total:", subtotal))
               .append(String.format("%-30s %10.2f\n", "Total:", finalTotal))
               .append("**************************************************************\n")
               .append(String.format("%30s\n", "*Thank You*"));

        view.getTxtArea().append(summary.toString());
    }

    private void resetForm() {
        for (useritempanel card : view.getItemCards()) {
            card.setQuantity(1);
            card.setSelected(false);
        }

        for (TextField field : view.getTextFields()) {
            field.setText("0.00");
        }

        view.getTxtArea().setText("");
        view.getTxtTax().setText("0.00");
        view.getTxtSubTotal().setText("0.00");
        view.getTxtTotal().setText("0.00");

        x = 0;
        total = 0.0;
        isConfirmed = false;
        orderMap.clear();
    }

    public void setTime() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuitemsController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Date date = new Date();
                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy");

                SwingUtilities.invokeLater(() -> {
                    view.getTxtTime().setText(timeFormat.format(date));
                    view.getTxtDate().setText(dateFormat.format(date));
                });
            }
        }).start();
    }

    public void addReceiptButtonListener() {
        view.getBtnReceipt().addActionListener(e -> {
            if (!isConfirmed) {
                JOptionPane.showMessageDialog(null,
                        "Please confirm the order before viewing receipt.",
                        "Order Not Confirmed",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            generateReceipt();
        });
    }

    private void generateReceipt() {
        String receiptContent = view.getTxtArea().getText();
        if (receiptContent.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No receipt content available mr prajal!");
            return;
        }

        ReceiptWindow receiptWindow = new ReceiptWindow(receiptContent);
        receiptWindow.setVisible(true);
    }

    private static class OrderEntry {
        String productName;
        double unitPrice;
        int qty;
        double total;

        public OrderEntry(String name, double price, int quantity) {
            this.productName = name;
            this.unitPrice = price;
            this.qty = quantity;
            this.total = price * quantity;
        }
    }
}
