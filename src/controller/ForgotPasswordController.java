/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ForgotPasswordDAO;
import view.ForgotPassword;

public class ForgotPasswordController {
    private final ForgotPasswordDAO dao = new ForgotPasswordDAO();
    private final ForgotPassword view;

    public ForgotPasswordController(ForgotPassword view) {
        this.view = view;

