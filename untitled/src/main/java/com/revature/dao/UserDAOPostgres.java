package com.revature.dao;

import com.revature.models.Users;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOPostgres implements UserDAO{
    @Override
    public Users getByEmail(String email) {
        Users user = new Users();

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM employees WHERE email = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){

                rs.next();

                int id = rs.getInt("id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String receivedEmail = rs.getString("email");
                String password = rs.getString("password");
                Boolean isManager = rs.getBoolean("isManager");

                user = new Users(id, first, last, receivedEmail,password, isManager);

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Users createUser(String first, String last, String email, String password, Boolean isManager) {

        Users user = new Users();

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO employees (\"first\", \"last\", email, \"password\", isManager) VALUES (?,?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setBoolean(5, isManager);

            ResultSet rs;

            if((rs = stmt.executeQuery()) != null) {

                rs.next();
                int id = rs.getInt("id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedEmail = rs.getString("email");
                String receivedPassword = rs.getString("password");
                Boolean receivedManager = rs.getBoolean("isManager");

                user = new Users(id, receivedFirst,receivedLast,receivedEmail,receivedPassword,receivedManager);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
        }


        return user;
    }

    @Override
    public List<Users> getAllUsers() {

        Connection conn = ConnectionUtil.getConnection();

        List<Users> users = new ArrayList<>();

        try{
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM employees";

            ResultSet rs = stmt.executeQuery(sql);



            while(rs.next()){
            int id = rs.getInt(("id"));
            String first = rs.getString(("first"));
            String last = rs.getString(("last"));
            String email = rs.getString(("email"));
            String password = rs.getString(("password"));
            Boolean isManager = rs.getBoolean(("isManager"));

            Users user = new Users(id, first, last, email, password, isManager);

            users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

//    @Override
//    public Users updateuser(Users user, int isUpdated) {
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "UPDATE employees SET isManager = ? WHERE email = ? RETURNING *";
//            assert conn != null;
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(2, user.getEmail());
//
//            if(isUpdated == 1){
//                stmt.setBoolean(1, true);
//            }else{
//                stmt.setBoolean(1, false);
//            }
//
//            ResultSet rs = stmt.executeQuery();
//            if(rs != null){
//                rs.next();
//                int id = rs.getInt("id");
//                String first = rs.getString(("first"));
//                String last = rs.getString(("last"));
//                String email = rs.getString(("email"));
//                String password = rs.getString(("password"));
//                Boolean isManager = rs.getBoolean(("isManager"));
//
//                user = new Users(id, first, last, email, password, isManager);
//
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Failed to update employee.");
//        }
//        return user;
//    }


}
