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

}
