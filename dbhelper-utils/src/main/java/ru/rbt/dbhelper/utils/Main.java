package ru.rbt.dbhelper.utils;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        String finalComand;
        System.out.println("Commands: \n" +
                "\tSHOW_TABLE\n" +
                "\tSHOW_COLUMN\n" +
                "\tSHOW_CELLS\n" +
                "\tSHOW_REV_TABLE\n" +
                "\tSHOW_LINE_LIKE_THIS\n" +
                "\tUPDATE_TABLE\n");

        finalComand = selector(args);

        if (finalComand != null) {
            System.out.println("\n\n\n\n" + finalComand);
        } else {
            System.out.println("Please enter valid command!");
        }
    }


}
