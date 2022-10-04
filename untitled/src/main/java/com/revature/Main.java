package com.revature;

import com.revature.models.Users;
import com.revature.service.UserService;
import java.util.Scanner;
public class Main {

    public static UserService us = new UserService();
    public static void main(String[] args) {
        System.out.println("Press 1 to Login. Press 2 to register.");

        Scanner sc = new Scanner(System.in);

        String choice = sc.nextLine();
        if (choice.equals("1")){
            // This is where we get login credentials

            // We need to get the username and password
            System.out.println("Please enter your email");
            String email = sc.nextLine();
            System.out.println("Please enter your password");
            String password = sc.nextLine();

            // Just for testing let's print it out
            //System.out.println("Username: " + username + ", Password: " + password);

            // AT THIS POINT we would call out TeacherService Class to handle the logic for signing in!

            us.login(email, password);

        } else if (choice.equals("2")){
            // This is where we get registration information
            System.out.println("Please enter your First Name");
            String first = sc.nextLine();
            System.out.println("Please enter your Last Name");
            String last = sc.nextLine();
            System.out.println("Please enter your email");
            String email = sc.nextLine();
            System.out.println("Please enter your password");
            String password = sc.nextLine();


            // Now that we have all the information we need we can start talking to our TeacherService class to handle this info
        }
    }
}