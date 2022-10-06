package com.revature.service;
import com.revature.dao.UserDAO;
//import com.revature.dao.UserDAOCSV;
import com.revature.dao.UserDAOPostgres;
import com.revature.dao.ValidationDAO;
import com.revature.models.Users;


import java.util.List;
import java.util.Scanner;


public class UserService {

    UserDAO userD = (UserDAO) new UserDAOPostgres();

    Scanner sc = new Scanner(System.in);
    public Users login(){

        System.out.println("Please enter your email");
        String email = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Users user = userD.getByEmail(email);

        if (user.getPassword().equals(password)) {
            System.out.println("Welcome Back.");
            System.out.println(user);
            return user;
        } else {
            System.out.println("You have not created an account with us.");
            return null;
        }
    }

    public Users register(){
        System.out.println("Please enter your First Name");
        String first = sc.nextLine();
        System.out.println("Please enter your Last Name");
        String last = sc.nextLine();
        String email = null;
        Boolean validate = false;
        while (!validate){
            System.out.println("Please enter your email");
            email = sc.nextLine();
            validate = ValidationDAO.validateEmail(email);
        }
        System.out.println("Please enter your password");
        String password = sc.nextLine();
        Boolean isManager = false;

        // Now we've taken in the information regarding the actual user we're trying to create and now we need to send
        // that info to the DAO

        // Now that we created the DAO method we just need to call it with the info provided
        Users user = userD.createUser(first, last, email, password, isManager);

        return user;
    }

    public void getAllUsers(){
        System.out.println("Using the database to return all of our employee objects");

        List<Users> userList = userD.getAllUsers();

        // Print each value of the list
        for(Users user: userList){
            System.out.println(user);
        }
    }

}
