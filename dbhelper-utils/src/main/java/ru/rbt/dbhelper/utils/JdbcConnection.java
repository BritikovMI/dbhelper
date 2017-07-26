package ru.rbt.dbhelper.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by BritikovMI on 26.07.2017.
 */
public class JdbcConnection {
    public static void driverSet(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection dbConnection(String url, String user, String pwd){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            System.out.println("\n\n\nThis exception in start connection in dbConnection \n\t\t" + e.getErrorCode() +"\n\n\n\n");
        }
        return connection;
    }
}
