package ru.rbt.dbhelper.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ru.rbt.dbhelper.utils.AlternateSelector.selector;
import static ru.rbt.dbhelper.utils.ConnectionManager.getConnection;

/**
 * Created by er23887 on 26.07.2017.
 */
public class QueryManager {

    public List<String> runQuery(Long name) {
        List<String> list = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
         Cmd cmd = selector("name");

        Connection con = getConnection();
//        try {
//            stmt = con.prepareStatement(dSel.toString());
//
//            rs = stmt.executeQuery(dSel.toString());
//            ResultSetMetaData metaData = rs.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            for (int i = 0; i < columnCount; i++) {
//                list.add(metaData.getColumnName(i + 1) + "");
//            }
//            list.add("\n");
//            while (rs.next()) {
//                for (int i = 0; i < columnCount; i++) {
//                    list.add(rs.getObject(i + 1) + "");
//                }
//                list.add("\n");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closeJdbc(con, stmt, rs);
//        }
        try {
            stmt = con.prepareStatement(cmd.toString());
            if (!cmd.isSelect()) {
                stmt.executeUpdate(cmd.toString());
                System.out.println("Record is updated to DBUSER table!");
            } else {
                rs = stmt.executeQuery(cmd.toString());
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    list.add(metaData.getColumnName(i + 1) + "");
                }
                list.add("\n");
                while (rs.next()) {
                    for (int i = 0; i < columnCount; i++) {
                        list.add(rs.getObject(i + 1) + "");
                    }
                    list.add("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Program error: " + e.getMessage());
        } finally {
            closeJdbc(con, stmt, rs);
        }
        return list;
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