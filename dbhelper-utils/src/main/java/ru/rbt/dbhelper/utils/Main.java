package ru.rbt.dbhelper.utils;

import java.sql.*;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;
import static ru.rbt.dbhelper.utils.JdbcConnection.dbConnection;
import static ru.rbt.dbhelper.utils.JdbcConnection.driverSet;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        String host = "dev.rbtechnologies.ru";
        int port = 1521;
        String sid = "ELAR";
        String user = "IRBIS";
        String pwd = "irbis";
        String url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);
        String finalComand;

        driverSet();
        try {
            connection = dbConnection(url, user, pwd);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("\n\n\n\n\n\n\n\t\t\t\t" + e.getErrorCode() + "\n\n\n\n\n\n\n");
        }


        System.out.println("Commands: \n" +
                "\tSHOW_TABLE\n" +
                "\tSHOW_COLUMN\n" +
                "\tSHOW_CELLS\n" +
                "\tSHOW_REV_TABLE\n" +
                "\tSHOW_LINE_LIKE_THIS\n" +
                "\tUPDATE_TABLE\n");

        finalComand = selector(args);

        if (finalComand != null) {
            System.out.println("\n\n\n\n Your command is: " + finalComand + ". The command is valid.");
        } else {
            System.out.println("Please enter valid command!");
        }


        try {

            ResultSet rs = statement.executeQuery(finalComand);

            while (rs.next()) {
//                String idPK = rs.getString("DATE_OF");
//                String dateOf = rs.getString("DATE_OF");
                String customeId = rs.getString("CUSTOMER_ID");
                System.out.println("customerId : " + customeId);
            }
            if (rs == null) {
                System.out.println("\n\nOhh, your statement is null!");
            } else {
                System.out.println("\n\nStatement is not null.");
            }
        } catch (SQLException e) {
            System.out.println("\n\n\n\n\n\n\n\t\t\t\t" + e.getErrorCode() + "\n\n\n\n\n\n\n");
        }


        try {
            connection.close();
            System.out.println("\n\nConnection was successfully closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
