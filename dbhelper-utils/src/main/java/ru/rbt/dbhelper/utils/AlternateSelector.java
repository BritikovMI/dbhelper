package ru.rbt.dbhelper.utils;

/**
 * Created by er23887 on 25.07.2017.
 */
public class AlternateSelector {

    public static String selector(String query) {

        String command = query;
        try {
            return Cmd.valueOf(command).toString();
        } catch (Exception e) {
            System.out.println("Please enter valid command! Error :   " +  e.getMessage());
            return null;
        }
    }
}