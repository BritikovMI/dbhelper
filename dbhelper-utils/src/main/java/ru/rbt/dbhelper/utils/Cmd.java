package ru.rbt.dbhelper.utils;

/**
 * Created by er23887 on 25.07.2017.
 */
public enum Cmd {
    SHOW_TABLE("SELECT * FROM BRM_ORDER"),
    SHOW_COLUMN("SELECT CUSTOMER_ID FROM BRM_ORDER"),
    SHOW_CELLS("SELECT DATE_OF FROM BRM_ORDER WHERE ID_PK != '2'"),
    SHOW_REV_TABLE("SELECT * FROM BRM_ORDER ORDER BY ID_PK DESC"),
    SHOW_LINE_LIKE_THIS("SELECT DATE_OF FROM BRM_ORDER WHERE DATE_OF LIKE '%2.07%'"),
    UPDATE_TABLE("UPDATE BRM_ORDER SET CUSTOMER_ID = '1',ID_PK = '2' WHERE DATE_OF = '12.07.2017'");
    private final String sql;
    Cmd(String sql) {
        this.sql = sql;
    }

    public boolean isSelect() {
        return sql.toLowerCase().startsWith("select");
    }

    @Override
    public String toString() {
        return sql;
    }
}