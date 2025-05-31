package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import zview.MenuItems;

public class MenuitemsController {

    private MenuItems view;
    private int x = 0;
    private double total = 0.0;

    public MenuitemsController(MenuItems view) {
        this.view = view;

        // ✅ Reset button click listener
        view.addbtnResetListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // ✅ Confirm button click listener
        view.addConfirmListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        init();
    }

    public void init() {
        checkboxMessage();
        setTime();
    }

public void CafeCraft() {
    String headers = String.format("%-5s %-20s %10s\n", "No.", "Product Name", "Price");
    view.getTxtArea().setText(
        "************************* CafeCraft *************************\n"
      + "Time: " + view.getTxtTime().getText() + "    Date: " + view.getTxtDate().getText() + "\n"
      + "**************************************************************\n"
      + headers
    );
}



    //this below code is for total and subtotal of the of the menu items 
    //calcuation of tax total of item and other other
 private void calculateTotal() {
    if (total == 0.0) {
        // ✅ If nothing was selected, show a warning and stop
        JOptionPane.showMessageDialog(null, "You haven’t purchased any items yet.");
        return;
    }

    double subtotal = total;
    double tax = subtotal * 0.13;
    double finalTotal = subtotal + tax;

    // ✅ Set values in their respective UI fields
    view.getTxtSubTotal().setText(String.format("%.2f", subtotal));
    view.getTxtTax().setText(String.format("%.2f", tax));
    view.getTxtTotal().setText(String.format("%.2f", finalTotal));

    // ✅ Append total summary to the JTextArea (receipt)
    String totalsBlock =
        "\n**************************************************************\n"
      + String.format("%-30s %10.2f\n", "Tax:", tax)
      + String.format("%-30s %10.2f\n", "Sub Total:", subtotal)
      + String.format("%-30s %10.2f\n", "Total:", finalTotal)
      + "**************************************************************\n"
      + String.format("%30s\n", "*Thank You*");

    view.getTxtArea().append(totalsBlock);
}

    public void setTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // Wait 1 second
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MenuitemsController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Get current date and time
                    Date date = new Date();

                    // Format time and date
                    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a");  // e.g., 3:45:12 PM
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy"); // e.g., Friday, 31-05-2025

                    // Format to string
                    String time = timeFormat.format(date);
                    String formattedDate = dateFormat.format(date);

                    // Set formatted time and date to view (using getter methods from view)
                    view.getTxtTime().setText(time);
                    view.getTxtDate().setText(formattedDate);
                }
            }
        }).start(); // Start the thread
    }

    // ✅ This method shows a warning only when called manually (no longer on startup)
    public void qtyIsZero() {
        for (JSpinner spinner : view.getSpinners()) {
            int qty = (Integer) spinner.getValue();
            if (qty == 0) {
                JOptionPane.showMessageDialog(null, "Please increase the item quantity.");
                break;
            }
        }
    }

    public void checkboxMessage() {
        JSpinner[] spinners = view.getSpinners();
        JCheckBox[] checkboxes = view.getCheckBoxes();
        JLabel[] labels = view.getLabels();
        double[] prices = view.getPrices(); // 🔥 Get dynamic prices

        for (int i = 0; i < checkboxes.length; i++) {
            int index = i;

            checkboxes[i].addActionListener(e -> {
                int qty = (Integer) spinners[index].getValue();

                // ️ Show warning if quantity is 0
                if (checkboxes[index].isSelected() && qty == 0) {
                    JOptionPane.showMessageDialog(null, "Please increase the quantity for item " + (index + 1));
                    checkboxes[index].setSelected(false);
                    return;
                }

                //  Valid selection
                if (checkboxes[index].isSelected()) {
                    x++; // increment item number

                    CafeCraft(); // sets header (time/date)

                    // Fix: Get actual label text (not object name)
                    String productName = labels[index].getText();

                    double unitPrice = prices[index];
                    double itemTotal = qty * unitPrice;

                    total += itemTotal; // ✅ Add this to accumulate total for calculation

                    // ✅ Append correct name and total
String line = String.format("%-5s %-20s %10s\n", x + ".", productName, String.format("%.2f", itemTotal));
view.getTxtArea().append(line);

                }
            });
        }
    }

    // ✅ Reset spinners, checkboxes, and text fields
    private void resetForm() {
        // ✅ Reset all JSpinners to 1
        for (JSpinner spinner : view.getSpinners()) {
            spinner.setValue(1);
        }

        // 👉 To add more spinners in the future:
        // return new JSpinner[] { jSpinner1, jSpinner2, jSpinner3, jSpinner4 };

        // ✅ Uncheck all JCheckBoxes
        for (JCheckBox checkbox : view.getCheckBoxes()) {
            checkbox.setSelected(false);
        }

        // 👉 To add more checkboxes in the future:
        // return new JCheckBox[] { jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4 };
        //add futher purchase chekcbox in view source 

        for (java.awt.TextField field : view.getTextFields()) {
            field.setText("0.0");
        }

        view.getTxtArea().setText("");
        x = 0;         // ✅ Reset item count
        total = 0.0;   // ✅ Reset total price
    }
}
