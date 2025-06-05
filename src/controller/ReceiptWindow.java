package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptWindow extends JFrame {

    private final JTextArea txtReceipt;

    public ReceiptWindow(String receiptContent) {
        setTitle("🧾 CafeCraft Receipt");
        setSize(520, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 📝 Receipt Display Area
        txtReceipt = new JTextArea(receiptContent);
        txtReceipt.setEditable(false);
        txtReceipt.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(true);
        txtReceipt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        txtReceipt.setBackground(new Color(250, 250, 250));

        JScrollPane scrollPane = new JScrollPane(txtReceipt);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        scrollPane.setPreferredSize(new Dimension(500, 500));

        // 🖨️ Print Button
        JButton btnPrint = new JButton("🖨️ Print");
        styleButton(btnPrint);
        btnPrint.addActionListener(e -> {
            try {
                boolean done = txtReceipt.print();
                if (!done) {
                    JOptionPane.showMessageDialog(this, "Printing canceled.");
                }
            } catch (HeadlessException | PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Printing failed: " + ex.getMessage());
            }
        });

        // 💾 Save Button
        JButton btnSave = new JButton("💾 Save");
        styleButton(btnSave);
        btnSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Receipt As...");
            int selected = fileChooser.showSaveDialog(this);
            if (selected == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().toLowerCase().endsWith(".txt")) {
                        file = new File(file.getAbsolutePath() + ".txt");
                    }
                    try (FileWriter writer = new FileWriter(file)) {
                        writer.write(txtReceipt.getText());
                    }
                    JOptionPane.showMessageDialog(this, "Receipt saved to:\n" + file.getAbsolutePath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Saving failed: " + ex.getMessage());
                }
            }
        });

        // 📦 Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));
        buttonPanel.add(btnPrint);
        buttonPanel.add(btnSave);

        // 📦 Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 🎨 Uniform button styling
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Times New Roman", Font.BOLD, 15));
        button.setBackground(new Color(40, 110, 160));
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
    }
}
