package ru.rbt.dbhelper.utils;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;

/**
 * Created by er23887 on 26.07.2017.
 */
public class QueryUtils {

    public static String getQuery(String args[]) {
        String sqlQuery = selector(args);
        if (sqlQuery == null || sqlQuery.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Commands: \n");
            for (Cmd cmd : Cmd.values()) {
                sb.append('\t').append(cmd.name()).append('\n');
            }
            throw new IllegalArgumentException("Unknown command.");
        }
        return sqlQuery;
    }
}
