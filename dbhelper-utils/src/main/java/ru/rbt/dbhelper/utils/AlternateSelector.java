package ru.rbt.dbhelper.utils;

/**
 * Created by er23887 on 25.07.2017.
 */
public class AlternateSelector {

    public static String selector(String[] args) {
        String command = args[0];
        try {
            return Cmd.valueOf(command).toString();
        } catch (Exception e) {
            System.out.println("Please enter valid command!");
            return null;
        }
    }
}
