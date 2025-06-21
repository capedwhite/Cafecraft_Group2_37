package controller;

import dao.ReservationDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Reservation;
import view.reservation;
import javax.swing.*;
import java.util.List;
import view.dashboard;

public final class ReservationController {
    private final reservation userView;
    private final ReservationDAO dao = new ReservationDAO();

    public ReservationController(reservation view) {
        this.userView = view;

        // ✅ Load available tables when the form opens
        loadAvailableTables();

        // ✅ Handle Submit button
        userView.addSubmitListener(e -> {
            try {
                String username = userView.getUsername();
                int noOfPeople = Integer.parseInt(userView.getPeople());
                String date = userView.getDate();
                String time = userView.getTime();

                // ✅ Ensure user selected a valid table (not the placeholder)
                if (userView.getComboBox().getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select a valid table from the list.");
                    return;
                }

                // ✅ Extract table number from selected item (e.g., "Table 1" -> 1)
                String selected = (String) userView.getComboBox().getSelectedItem();
                int tableNo = Integer.parseInt(selected.replace("Table ", ""));

                // ✅ Save reservation
                Reservation r = new Reservation(username, noOfPeople, date, time, tableNo);
                boolean saved = dao.saveReservation(r);

                if (saved) {
                    JOptionPane.showMessageDialog(null, "Reservation Successful");
                    userView.clearFields(); // clear form
                    loadAvailableTables(); // refresh available tables
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Save");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input: " + ex.getMessage());
            }
        });

        // ✅ Handle Cancel button (exit the form)
        userView.addCancelListener(new cancelbtn());
    }

    public void loadAvailableTables() {
        List<Integer> available = dao.getAvailableTables(10); // assuming 10 tables total
        JComboBox<String> combo = userView.getComboBox();
        combo.removeAllItems();

        // ✅ Add placeholder first
        combo.addItem("Available Tables");

        // ✅ Add only available tables
        for (int t : available) {
            combo.addItem("Table " + t);
        }

        // Optional: set the default selected item to placeholder
        combo.setSelectedIndex(0);
    }

    class cancelbtn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           userView.dispose();
           dashboard Dashboard = new dashboard();
           Dashboardcontroller dashboardcontrol = new Dashboardcontroller(Dashboard);
           Dashboard.setVisible(true);
        }
        
    }
}
