package com.revature.service;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOCSV;
import com.revature.models.Users;


public class UserService {

    UserDAO userD = (UserDAO) new UserDAOCSV();

    public void login(String email, String password){

        Users user = userD.getByEmail(email);
        if (user.getPassword().equals(password)) {
            System.out.println("Welcome Back.");
            System.out.println(user);
        } else {
            System.out.println("You have not created an account with us.");
        }
    }

}
