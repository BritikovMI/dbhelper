package ru.rbt.dbhelper.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by er23887 on 26.07.2017.
 */
public class JdbcProperties {

    private Map<String, String> cashed = new HashMap<String, String>();

    private static JdbcProperties ourInstance = new JdbcProperties();

    public static JdbcProperties getInstance() {
        return ourInstance;
    }

    private JdbcProperties() {
        cashed.put("driver", "oracle.jdbc.driver.OracleDriver");
        cashed.put("host", "dev.rbtechnologies.ru");
        cashed.put("port", "1521");
        cashed.put("sid", "ELAR");
        cashed.put("user", "IRBIS");
        cashed.put("pwd", "irbis");
    }

    public String getDriver() {
        return cashed.get("driver");
    }

    public String getUrl() {
        return String.format("jdbc:oracle:thin:@%s:%s:%s",
                cashed.get("host"), cashed.get("port"), cashed.get("sid"));
    }

    public String getUsername() {
        return cashed.get("user");
    }

    public String getPassword() {
        return cashed.get("pwd");
    }
}