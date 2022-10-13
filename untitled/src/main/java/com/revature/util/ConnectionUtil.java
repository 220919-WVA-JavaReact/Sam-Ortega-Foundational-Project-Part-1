package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    private static Connection conn = null;

    private ConnectionUtil() {

    }

    public static Connection getConnection(){

        try{
            if (conn != null && !conn.isClosed()) {

                System.out.println("Use a previously made connection");
                return conn;
            }

        }catch (SQLException e){

            e.printStackTrace();
            return null;
        }

        String url = System.getenv("url");
        String username = System.getenv("username");
        String password = System.getenv("password");

        try {
            Class.forName("org.postgresql.Driver");
            conn =DriverManager.getConnection(url, username, password);
            System.out.println("Connecting..");
        } catch (SQLException e) {
            System.out.println("Couldn't establish connection");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

}
