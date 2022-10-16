package com.revature.service;
import com.revature.dao.UserDAO;
//import com.revature.dao.UserDAOCSV;
import com.revature.dao.UserDAOPostgres;
import com.revature.dao.ValidationDAO;
import com.revature.models.Users;


import java.util.List;
import java.util.Scanner;


public class UserService {
    public UserService() {
        System.out.println("UserService Initialized");
    }

    UserDAO userD = (UserDAO) new UserDAOPostgres();

    Scanner sc = new Scanner(System.in);
    public Users login(String email, String password){
    Users user = userD.getByEmail(email);

        if (!user.getPassword().equals(password)) {
            return null;
        } else {
            return user;
        }
    }

    public Users register(String first, String last, String email, String password, Boolean isManager){
        return userD.createUser(first, last, email, password, isManager);
    }

    public void getAllUsers(){
        System.out.println("Using the database to return all of our employee objects");

        List<Users> userList = userD.getAllUsers();

        // Print each value of the list
        for(Users user: userList){
            System.out.println(user);
        }
    }



//    public void employeePromotion(Users loggedInUser){
//        System.out.println("Update employee by email.");
//        String isPromoted = sc.nextLine();
//        Users user = userD.getByEmail(isPromoted);
//
//        if(!user.getEmail().equals(loggedInUser.getEmail())){
//            System.out.println("Update to 1) Manager or 2) Employee?");
//            int isUpdated = sc.nextInt();
//            sc.nextLine();
//
//            userD.updateuser(user, isUpdated);
//        }
//    }

}
