package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import zview.MenuItems;

public class MenuitemsController {

    private final MenuItems view;
    private int x = 0;
    private double total = 0.0;
    private boolean isConfirmed = false;
    private final Map<String, OrderEntry> orderMap = new LinkedHashMap<>();

    public MenuitemsController(MenuItems view) {
        this.view = view;

        // Button Listeners
        view.addbtnResetListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        view.addConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConfirmed) {
                    JOptionPane.showMessageDialog(null, "Order already confirmed!");
                    return;
                }
                calculateTotal();
                isConfirmed = true;
            }
        });

        addReceiptButtonListener(); // ✅ Add listener for new receipt window
        init();
    }

    public void init() {
        checkboxMessage();
        setTime();
    }

    public void CafeCraft() {
        String headers = String.format("%-5s %-20s %-10s %-10s %-10s\n", "No.", "Item", "Qty", "Rate", "Total");
        view.getTxtArea().setText(
            "************************* CafeCraft *************************\n"
          + "Time: " + view.getTxtTime().getText() + "    Date: " + view.getTxtDate().getText() + "\n"
          + "**************************************************************\n"
          + headers
        );
    }

    private void calculateTotal() {
        if (orderMap.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven’t purchased any items yet.");
            return;
        }

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

    public void checkboxMessage() {
        JSpinner[] spinners = view.getSpinners();
        JCheckBox[] checkboxes = view.getCheckBoxes();
        JLabel[] labels = view.getLabels();
        double[] prices = view.getPrices();

        for (int i = 0; i < checkboxes.length; i++) {
            int index = i;

            checkboxes[i].addActionListener(e -> {
                int qty = (Integer) spinners[index].getValue();
                String productName = labels[index].getText();
                double rate = prices[index];

                if (checkboxes[index].isSelected() && qty == 0) {
                    JOptionPane.showMessageDialog(null, "Please increase the quantity for item " + (index + 1));
                    checkboxes[index].setSelected(false);
                    return;
                }

                if (checkboxes[index].isSelected()) {
                    CafeCraft();

                    if (orderMap.containsKey(productName)) {
                        OrderEntry existing = orderMap.get(productName);
                        existing.qty += qty;
                        existing.total = existing.qty * rate;
                    } else {
                        OrderEntry newEntry = new OrderEntry(productName, rate, qty);
                        orderMap.put(productName, newEntry);
                    }

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
            });
        }
    }

    private void resetForm() {
        for (JSpinner spinner : view.getSpinners()) {
            spinner.setValue(1);
        }

        for (JCheckBox checkbox : view.getCheckBoxes()) {
            checkbox.setSelected(false);
        }

        for (TextField field : view.getTextFields()) {
            field.setText("0.0");
        }

        view.getTxtArea().setText("");
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

                String time = timeFormat.format(date);
                String formattedDate = dateFormat.format(date);

                view.getTxtTime().setText(time);
                view.getTxtDate().setText(formattedDate);
            }
        }).start();
    }

    // ✅ Shows ReceiptWindow only if order is confirmed
    public void addReceiptButtonListener() {
        view.getBtnReceipt().addActionListener(e -> {
            if (!isConfirmed) {
                JOptionPane.showMessageDialog(null, "Please confirm your order first.");
                return;
            }

            String receiptContent = view.getTxtArea().getText();
            ReceiptWindow receiptWindow = new ReceiptWindow(receiptContent); // if in controller
            receiptWindow.setVisible(true);
        });
    }

    // Data structure for orders
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
