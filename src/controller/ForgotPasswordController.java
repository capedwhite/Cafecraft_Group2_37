/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.ForgotPasswordDAO;
import model.User;

/**
 *
 * @author Unish K.C
 */
public class ForgotPasswordController {
        public void resetPassword(String username, String newPassword,String email) {
        User user = new User(username, newPassword , email); // User object
        ForgotPasswordDAO dao = new ForgotPasswordDAO();
        dao.updatePassword(user);
        }
}





