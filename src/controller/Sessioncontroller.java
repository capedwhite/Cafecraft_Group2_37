/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;

/**
 *
 * @author ASUS
 */
public class Sessioncontroller {
   private static int currentUser;
   public static void setcurrentuser(int user_Id){
       currentUser=user_Id;
   }
   public static int getcurrentuser(){
       return currentUser;
}
}

