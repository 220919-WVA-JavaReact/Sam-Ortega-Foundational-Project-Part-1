package com.revature.util;

import java.io.File;
import java.io.FileNotFoundException;
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

        String url = "";
        String username = "";
        String password = "";
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("\\\\wsl.localhost\\Ubuntu-20.04\\home\\sam\\foundational-project\\untitled\\src\\main\\resources\\application.properties"));
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e) {
            System.out.println("Couldn't establish connection");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        return conn;
    }

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL Driver");
            throw new RuntimeException(e);
        }
    }

}
