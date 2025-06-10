package controller;

import view.admin_sidebar;

public class TestDashboard {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                admin_sidebar sidebar = new admin_sidebar();
                new admindasboardcontroller(sidebar); // ✅ Wires all buttons
                sidebar.setVisible(true);
            }
        });
    }
}
