package controller;

import dao.AboutCafeDAO;
import model.AboutCafe;
import view.aboutcafe;

import javax.swing.*;

public class AboutCafeController {
    private final aboutcafe view;
    private final AboutCafeDAO dao;
    private final AboutCafe aboutcafemodel;

    public AboutCafeController(aboutcafe view) {
        this.view = view;
        this.dao = new AboutCafeDAO();
        this.aboutcafemodel=new AboutCafe();

        // ✅ Load the content into the text area
        String aboutcafe = dao.getText(aboutcafemodel.getId());
        view.setAboutText(aboutcafe);
        view.setEditable(false);  // Start in read-only mode
        System.out.println("AboutCafeController initialized and content loaded.");

        // ✅ Edit Button: unlock text area
        view.addEditButtonListener(e -> {
            System.out.println("Edit button clicked");
            view.setEditable(true);
        });

        // ✅ Save Button: save content to DB
        view.addSaveButtonListener(e -> {
            System.out.println(" Save button clicked");
            String newText = view.getAboutText();
            boolean success = dao.updateAboutText(newText);
            JOptionPane.showMessageDialog(view,
                    success ? "Saved successfully!" : "Failed to save.");
            view.setEditable(false);
        });

        // ✅ Exit Button: close the window
        view.addExitButtonListener(e -> {
            System.out.println("Exit button clicked");
            view.dispose();
        });
    }
}
