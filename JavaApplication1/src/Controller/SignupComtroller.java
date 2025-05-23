/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author user
 */
import view.Signup;
import Dao.UserDao;
public class SignupComtroller {
    private final UserDao UserDao = new UserDao();
    private final SignUp userView;

    public SignupComtroller(SignUp userView) {
        this.userView = userView;

        userView.addAddUserListener(new AddUserListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
}
