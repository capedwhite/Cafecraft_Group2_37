/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.logindao;
import model.User;
/**
 *
 * @author user
 */
public class Logincontroller {
        public boolean authenticate(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        User user = new User(username, password);
        return logindao.validateUser(user);
}
