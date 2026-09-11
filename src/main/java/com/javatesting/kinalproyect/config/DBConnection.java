package main.java.com.javatesting.kinalproyect.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException{
        if(connection == null || connection .isClosed()){
           connection = DriverManager.getConnection(Credentials.URL_DB, Credentials.USER_DB, Credentials.PASS_DB);
        }
        return connection;
    }
}
