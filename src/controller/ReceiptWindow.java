package controller;

import javax.swing.*;

public class ReceiptWindow extends JFrame {
    private final JTextArea txtReceipt;

    public ReceiptWindow(String receiptContent) {
        setTitle("CafeCraft Receipt");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        txtReceipt = new JTextArea();
        txtReceipt.setText(receiptContent);
        txtReceipt.setEditable(false);
        txtReceipt.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(txtReceipt);

        JButton btnPrint = new JButton("Print");
        btnPrint.addActionListener(e -> {
            try {
                boolean done = txtReceipt.print();
                if (!done) {
                    JOptionPane.showMessageDialog(this, "Printing canceled.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Printing failed: " + ex.getMessage());
            }
        });

        JButton btnDownload = new JButton("Download");
        btnDownload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int selected = fileChooser.showSaveDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION) {
                try {
                    java.io.File file = fileChooser.getSelectedFile();
                    java.io.FileWriter writer = new java.io.FileWriter(file);
                    writer.write(txtReceipt.getText());
                    writer.close();
                    JOptionPane.showMessageDialog(this, "Receipt saved successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Saving failed: " + ex.getMessage());
                }
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnPrint);
        bottomPanel.add(btnDownload);

        add(scrollPane, "Center");
        add(bottomPanel, "South");
    }
}
