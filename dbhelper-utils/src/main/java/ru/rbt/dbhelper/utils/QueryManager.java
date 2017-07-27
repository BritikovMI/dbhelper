package ru.rbt.dbhelper.utils;

import java.sql.*;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;
import static ru.rbt.dbhelper.utils.ConnectionManager.getConnection;

/**
 * Created by er23887 on 26.07.2017.
 */
public class QueryManager {

    public void runQuery(String query) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String startCommand = query;
        query = selector(query);
        String table = String.format("\n\n\n\n%s\t%s\t%s", "ID_PK", "DATE_OF", "CUSTOMER_ID");
        try {
            con = getConnection();
            stmt = con.createStatement();
            if (startCommand == "UPDATE_TABLE") {
                stmt.execute(query);
                System.out.println("Record is updated to DBUSER table!");
            } else {
                System.out.println(table);
                rs = stmt.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                while (rs.next()) {
                    for (int i = 0; i < columnCount; i++) {
                        System.out.print(rs.getObject(i + 1));
                        System.out.print('\t');
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Program error: " + e.getMessage());
        } finally {
            closeJdbc(con, stmt, rs);
        }
    }

    private void closeJdbc(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}