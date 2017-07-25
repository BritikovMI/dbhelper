package ru.rbt.dbhelper.utils;

/**
 * Created by er23887 on 25.07.2017.
 */
public enum Cmd {
    SHOW_TABLE("SELECT * FROM BRM_ORDER"),
    SHOW_COLUMN("SELECT CUSTOMER_ID FROM BRM_ORDER");
    private final String sql;
    Cmd(String sql) {
        this.sql = sql;
    }
    @Override
    public String toString() {
        return sql;
    }
}
