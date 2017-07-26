package ru.rbt.dbhelper.utils;

import static ru.rbt.dbhelper.utils.QueryUtils.getQuery;

/**
 * Created by BritikovMI on 25.07.2017.
 */
public class Main {
    public static void main(String args[]) {
        new QueryManager().runQuery(getQuery(args));
    }
}
