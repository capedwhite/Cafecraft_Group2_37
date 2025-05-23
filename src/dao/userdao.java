/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import model.User;
import java.sql.ResultSet;


public class userdao {
     Mysqlconnection mysql= new Mysqlconnection();
  
public void signup(User user){
Connection conn=mysql.openConnection();

String sql = "INSERT INTO users(username,email,password)VALUES(?,?,?)";
try(PreparedStatement pstmt=conn.prepareStatement(sql)){
pstmt.setString(1,user.getUsername());
pstmt.setString(2,user.getEmail());
pstmt.setString(3,user.getPassword());
pstmt.executeUpdate();
}
catch(SQLException ex){
Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,ex);

}finally{
mysql.CloseConnection(conn);
}
}


public boolean checkUser(User user){
Connection conn=mysql.openConnection();
String sql="SELECT * FROM users WHERE email = ? OR username = ?";
try(PreparedStatement pstmt = conn.prepareStatement(sql)){
pstmt.setString(1,user.getEmail());
pstmt.setString(2,user.getUsername());
ResultSet result = pstmt.executeQuery();
return result.next();
}
catch(SQLException ex){
Logger.getLogger(userdao.class.getName()).log(Level.SEVERE,null,ex);
}
finally{
mysql.CloseConnection(conn);
}
return false;
}
}
