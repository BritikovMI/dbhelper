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
        ResultSet rs;
        String table;
        //                  SHOW_TABLE                                  SHOW_COLUMN                                           SHOW_CELLS                                                           SHOW_REV_TABLE                                                   SHOW_LINE_LIKE_THIS
        if (finalComand == "SELECT * FROM BRM_ORDER" || finalComand == "SELECT CUSTOMER_ID FROM BRM_ORDER" || finalComand == "SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK != '2'" || finalComand == "SELECT * FROM BRM_ORDER ORDER BY ID_PK DESC" || finalComand == "SELECT DATE_OF FROM BRM_ORDER WHERE DATE_OF LIKE '%2.07%'") {
            try {

                rs = statement.executeQuery(finalComand);

                //SHOW_TABLE or SHOW_REV_TABLE
                if (finalComand == "SELECT * FROM BRM_ORDER" || finalComand == "SELECT * FROM BRM_ORDER ORDER BY ID_PK DESC") {
                    table = String.format("\n\n\n\n%s\t%s\t\t%s", "ID_PK", "DATE_OF", "CUSTOMER_ID");
                    System.out.println(table + "\n");
                    while (rs.next()) {

                        String idPk = rs.getString("ID_PK");
                        String dateOf = rs.getString("DATE_OF");
                        String customeId = rs.getString("CUSTOMER_ID");
                        String output = String.format("%s\t%s\t\t%s", idPk, dateOf, customeId);
                        System.out.println(output + "\n");
                    }
                }

                //SHOW_COLUMN
                if (finalComand == "SELECT CUSTOMER_ID FROM BRM_ORDER") {
                    table = String.format("%s", "CUSTOMER_ID");
                    System.out.println(table + "\n");
                    while (rs.next()) {
                        String customeId = rs.getString("CUSTOMER_ID");
                        String output = String.format("%s", customeId);
                        System.out.println(output + "\n");
                    }
                }

                //SHOW_CELLS
                if (finalComand == "SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK != '2'") {
                    table = String.format("\n\n%s", "DATE_OF");
                    System.out.println(table + "\n");
                    while (rs.next()) {
                        String dateOf = rs.getString("DATE_OF");
                        String output = String.format("%s", dateOf);
                        System.out.println(output + "\n");
                    }
                }
                //SHOW_LINE_LIKE_THIS
                if (finalComand == "SELECT DATE_OF FROM BRM_ORDER WHERE DATE_OF LIKE '%2.07%'") {
                    table = String.format("\n\n%s", "DATE_OF");
                    System.out.println(table + "\n");
                    while (rs.next()) {
                        String dateOf = rs.getString("DATE_OF");
                        String output = String.format("%s", dateOf);
                        System.out.println(output + "\n");
                    }
                }

                if (rs == null) {
                    System.out.println("\n\nOhh, your statement is null!");
                } else {
                    System.out.println("\n\nStatement is not null.");
                }
            } catch (SQLException e) {
                System.out.println("\n\n\n\n\n\n\n\t\t\t\t" + e.getErrorCode() + "\n\n\n\n\n\n\n");
            }
        }

        if (finalComand == "UPDATE BRM_ORDER SET CUSTOMER_ID = '4',ID_PK = '2' WHERE DATE_OF = '12.07.2017'") {

            try {

                table = String.format("\n\n\n\n%s\t%s\t\t%s", "ID_PK", "DATE_OF", "CUSTOMER_ID");
                System.out.println("\n\n\n\nBefore:\n\n");
                System.out.println(table + "\n");
                rs = statement.executeQuery("SELECT * FROM BRM_ORDER");
                while (rs.next()) {

                    String idPk = rs.getString("ID_PK");
                    String dateOf = rs.getString("DATE_OF");
                    String customeId = rs.getString("CUSTOMER_ID");
                    String output = String.format("%s\t%s\t\t%s", idPk, dateOf, customeId);
                    System.out.println(output + "\n");
                }

                //Кидаем команду изменения
                statement.execute(finalComand);



                System.out.println("\n\n\n\nAfter:\n\n");
                System.out.println(table + "\n");
                rs = statement.executeQuery("SELECT * FROM BRM_ORDER");
                while (rs.next()) {

                    String idPk = rs.getString("ID_PK");
                    String dateOf = rs.getString("DATE_OF");
                    String customeId = rs.getString("CUSTOMER_ID");
                    String output = String.format("%s\t%s\t\t%s", idPk, dateOf, customeId);
                    System.out.println(output + "\n");
                }
            } catch (SQLException e) {
                System.out.println("\n\n\n\n\n\n\n\t\t\t\t" + e.getErrorCode() + "\n\n\n\n\n\n\n");
            }
        }

        try {
            connection.close();
            System.out.println("\n\nConnection was successfully closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
