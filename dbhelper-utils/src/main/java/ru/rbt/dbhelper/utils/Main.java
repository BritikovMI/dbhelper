package ru.rbt.dbhelper.utils;

import oracle.jdbc.OracleDriver;

import java.sql.*;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {
    final private static String driverName = "oracle.jdbc.driver.OracleDriver";
    private static String url;
    final private static String server = "dev.rbtechnologies.ru";
    final private static String port = "1521";
    final private static String sid = "ELAR";
    final private static String username = "IRBIS";
    final private static String password = "irbis";
    public static void main(String[] args) {
        url = String.format("jdbc:oracle:thin:@%s:%s:%s", server, port, sid);

//        String host = "dev.rbtechnologies.ru";
//        int port = 1521;
//        String sid = "ELAR";
//        String user = "IRBIS";
//        String pwd = "irbis";
//        String url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);
//
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, user, pwd);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Connection connection;
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Системе не удалось подключить драйвер");
            e.printStackTrace();
        }


        String finalComand;
//        System.out.println("Commands: \n" +
//                "\tSHOW_TABLE\n" +
//                "\tSHOW_COLUMN\n" +
//                "\tSHOW_CELLS\n" +
//                "\tSHOW_REV_TABLE\n" +
//                "\tSHOW_LINE_LIKE_THIS\n" +
//                "\tUPDATE_TABLE\n");

        finalComand = selector(args);

        if (finalComand != null) {
            System.out.println("\n\n\n\n" + finalComand);
        } else {
            System.out.println("Please enter valid command!");
        }
    }


}
