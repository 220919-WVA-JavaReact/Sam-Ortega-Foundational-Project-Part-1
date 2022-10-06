package com.revature.dao;

import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ValidationDAO {
    public static boolean validateEmail(String email){

        Boolean validate = false;

        try (Connection conn = ConnectionUtil.getConnection()) {
            String query = "SELECT COUNT(*) FROM employees WHERE email = ?";
            conn.prepareStatement(query);

            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            rs.next();
            if (rs.getInt("count") != 0) {
                System.out.println("Email has already been taken!");
            } else {
                validate = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return validate;
    }


}
