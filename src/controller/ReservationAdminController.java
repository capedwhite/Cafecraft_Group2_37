package controller;

import dao.ReservationDAO;
import model.Reservation;
import view.reservationadmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ReservationAdminController {
    private final reservationadmin view;
    private final ReservationDAO dao;

    public ReservationAdminController(reservationadmin view) {
        this.view = view;
        this.dao = new ReservationDAO();

        loadReservations(); // load on open

        view.addMarkCompleteListener(e -> updateCompletedReservations()); // ✅ existing feature
        view.addResetListener(e -> resetAllReservations());    // ✅ added by Prajal
        view.addExitListener(e -> view.dispose());
    }

    public void loadReservations() {
        List<Reservation> reservations = dao.getAllReservations();
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0); // clear table

        for (Reservation r : reservations) {
            model.addRow(new Object[]{
                r.getUsername(),
                r.getNoOfPeople(),
                r.getDate(),
                r.getTime(),
                r.getTableNo(),
                r.getStatus(),
                false // checkbox
            });
        }
    }

    private void updateCompletedReservations() {
        DefaultTableModel model = view.getTableModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isChecked = (boolean) model.getValueAt(i, 6); // checkbox column
            String status = (String) model.getValueAt(i, 5);

            if (isChecked && !"complete".equalsIgnoreCase(status)) {
                int tableNo = (int) model.getValueAt(i, 4);
                dao.markReservationComplete(tableNo);
            }
        }

        loadReservations(); // refresh
    }

    private void resetAllReservations() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to delete all reservations?",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = dao.deleteAllReservations();
            if (deleted) {
                JOptionPane.showMessageDialog(null, "All reservation records deleted.");
                loadReservations(); // refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete reservations.");
            }
        }
    }
}
