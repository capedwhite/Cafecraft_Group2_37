<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class User {
<<<<<<< HEAD
    private int id;
    private String username;
    private String password;
    private String email;

public User(String username,String password,String email){
this.username=username;
this.password=password;
this.email=email;
}
public int getID(){
return id;
}
public String getUsername(){
return username;
}
public String getPassword(){
return password;
}
public String getEmail(){
    return email;
}

=======
    
    private String username;
    private String password;

    // Constructor (empty)
    public User() {
    }

    // Constructor (with username and password)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
>>>>>>> origin/forgotpassword
}

=======
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package model;
//
///**
// *
// * @author ASUS
// */
//public class User {
//    
//}
>>>>>>> origin/menuitem
