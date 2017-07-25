package ru.rbt.dbhelper.utils;

import java.util.Scanner;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        String finalComand;
        Selector sel = new Selector();

        System.out.println("Commands: \n" +
                "\tSHOW_TABLE\n" +
                "\tSHOW_COLUMN\n" +
                "\tSHOW_CELLS\n" +
                "\tSHOW_REV_TABLE\n" +
                "\tSHOW_LINE_LIKE_THIS\n" +
                "\tUPDATE_TABLE\n" +
                "\n\nPlease enter some command below:\n");

        finalComand = sel.selector(args);

        if (finalComand != null) {
            System.out.println("\n\n\n\n" + finalComand);
        } else {
            System.out.println("Please enter valid command!");
        }
    }


}

class Selector {
    static final String SHOW_TABLE = "SELECT * FROM BRM_ORDER;";
    static final String SHOW_COLUMN = "SELECT CUSTOMER_ID FROM BRM_ORDER;";
    static final String SHOW_CELLS = "SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK != '2';";
    static final String SHOW_REV_TABLE = "SELECT * FROM BRM_ORDER ORDER BY ID_PK DESC;";
    static final String SHOW_LINE_LIKE_THIS = "SELECT DATE_OF FROM BRM_ORDER WHERE DATE_OF LIKE '%2.07%';";
    static final String UPDATE_TABLE = "UPDATE BRM_ORDER SET CUSTOMER_ID = '2',ID_PK = '2' WHERE DATE_OF = '12.07.2017';";

    public static String selector(String[] args) {
        String command = args[0];
        String finComand = null;
        switch (command) {
            case "SHOW_TABLE":
                finComand = SHOW_TABLE;
                break;
            case "SHOW_COLUMN":
                finComand = SHOW_COLUMN;
                break;
            case "SHOW_CELLS":
                finComand = SHOW_CELLS;
                break;
            case "SHOW_REV_TABLE":
                finComand = SHOW_REV_TABLE;
                break;
            case "SHOW_LINE_LIKE_THIS":
                finComand = SHOW_LINE_LIKE_THIS;
                break;
            case "UPDATE_TABLE":
                finComand = UPDATE_TABLE;
                break;
            default:
                System.out.println("Please enter valid command!");
        }
        return finComand;
    }
}
