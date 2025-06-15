package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author asimq
 */
public class Mysqlconnection implements Database {

    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "viscabarca10";
            String database = "cafecraft";
            Connection connection;
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/" + database, username, password);

            if (connection == null) {
                System.out.println("Database connection failed");
            } else {
                System.out.println("Database connection success");
            }
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(query);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public void CloseConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}