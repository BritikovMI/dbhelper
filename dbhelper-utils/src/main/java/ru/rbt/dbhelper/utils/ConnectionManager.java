package ru.rbt.dbhelper.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by BritikovMI on 26.07.2017.
 */
public class ConnectionManager {

    public static Connection getConnection() {
        try {
            JdbcProperties jdbcProperties = JdbcProperties.getInstance();
            Class.forName(jdbcProperties.getDriver());
            return DriverManager.getConnection(
                    jdbcProperties.getUrl(),
                    jdbcProperties.getUsername(),
                    jdbcProperties.getPassword());
        } catch (SQLException e) {
            System.out.println("While getting JDBC connection error occurred: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found: " + e.getMessage());
        }
        return null;
    }
}