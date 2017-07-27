package ru.rbt.dbhelper.utils;

import static javafx.application.Platform.exit;
import static ru.rbt.dbhelper.utils.AlternateSelector.selector;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {
    public static void main(String args[]) {
        String query = args[0];
        new QueryManager().runQuery(query);
    }
}