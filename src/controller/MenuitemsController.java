package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import zview.MenuItems;

public class MenuitemsController {

    private MenuItems view;

    public MenuitemsController(MenuItems view) {
        this.view = view;

        // ✅ Reset button click listener
        view.addbtnResetListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm(); 
            }
        });

        init(); 
    }

    public void init() {
        checkboxMessage(); 
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

    // ✅ Show warning if user checks item with 0 quantity
    public void checkboxMessage() {
        JSpinner[] spinners = view.getSpinners();     
        JCheckBox[] checkboxes = view.getCheckBoxes(); 

        for (int i = 0; i < checkboxes.length; i++) {
            int index = i; // required for lambda

            checkboxes[i].addActionListener(e -> {
                int qty = (Integer) spinners[index].getValue();

                // ✅ If item is selected but quantity is 0
                if (checkboxes[index].isSelected() && qty == 0) {
                    JOptionPane.showMessageDialog(null, "Please increase the quantity for item " + (index + 1));
                    checkboxes[index].setSelected(false); // ✅ uncheck the box
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

      for (java.awt.TextField field : view.getTextFields()) {
            field.setText("0.0"); 
        }
        
    }
}
