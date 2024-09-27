package com.craftelix.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@UtilityClass
public class ConnectionManager {

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName(PropertiesUtil.get("driver-class-name"));
            log.info("Driver class loaded");
        } catch (ClassNotFoundException e) {
            log.error("FATAL ERROR: Driver class not found", e);
            throw new RuntimeException(e);
        }
    }

    public static Connection open() {
        try {
            String url = PropertiesUtil.get("url");
            String username = PropertiesUtil.get("username");
            String password = PropertiesUtil.get("password");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("FATAL ERROR: Failed to open connection", e);
            throw new RuntimeException(e);
        }
    }
}
