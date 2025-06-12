package controller;

import dao.AboutCafeDAO;
import model.AboutCafe;
import view.aboutcafe;

public class AboutCafeUserController {
    private final aboutcafe view;
    private final AboutCafeDAO dao;
    private final AboutCafe aboutcafee;

    public AboutCafeUserController(aboutcafe view) {
        this.view = view;
        this.dao = new AboutCafeDAO();
        this.aboutcafee=new AboutCafe();

        // Load content only
        String aboutcafe = dao.getText(aboutcafee.getId());
        view.setAboutText(aboutcafe);
        view.setEditable(false); // Lock editing

     
        view.hideAdminButtons();
    }
}
