
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
    private String Securityqn;
    private String Answer;

public User(String username,String password,String Securityqn,String Answer){
this.username=username;
this.password=password;
this.Securityqn=Securityqn;
this.Answer=Answer;
}
public User(String username,String password){
    this.username=username;
    this.password=password;
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
public String getSecurityqn(){
return Securityqn;
}
public String getAnswer(){
    return Answer;
}

}