package ru.rbt.dbhelper.utils;

/**
 * Created by er23887 on 25.07.2017.
 */
public class AlternateSelector {

    public static Cmd selector(String name) {
        try {
            return Cmd.valueOf(name);
        } catch (Exception e) {
            System.out.println("Please enter valid command! Error :   " +  e.getMessage());
            return null;
        }
    }
}