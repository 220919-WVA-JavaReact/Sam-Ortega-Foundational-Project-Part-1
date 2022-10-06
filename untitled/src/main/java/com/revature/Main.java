package com.revature;

import com.revature.models.Users;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.util.Scanner;
public class Main {

    public static UserService us = new UserService();
    public static ReimbursementService rs = new ReimbursementService();
    public static void main(String[] args) {
        System.out.println("   ____________________"+
        "\n  / ================== \\"+
        "\n /______________________\\"+
       "\n| ______________________ |"+
        "\n| |                    | |"+
        "\n| |1)To Login.         | |"+
        "\n| |                    | |"+
        "\n| |2)To register.      | |"+
        "\n| |                    | |"+
        "\n| |3)View all employees| |"+
        "\n| |____________________| |"+
       "\n\\_=______________________/" +
       "\n /\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\" \\"+
        "\n/:::::::::::::::::::::::: \\"+
       "\n(___________________________)");

        Scanner sc = new Scanner(System.in);

        String choice = sc.nextLine();

        Users loggedInUser = null;

        if (choice.equals("1")){
            // This is where we get login credentials

            // We need to get the username and password
//            System.out.println("Please enter your email");
//            String email = sc.nextLine();
//            System.out.println("Please enter your password");
//            String password = sc.nextLine();

            // Just for testing let's print it out
            //System.out.println("Username: " + username + ", Password: " + password);

            // AT THIS POINT we would call out TeacherService Class to handle the logic for signing in!

            loggedInUser = us.login();

        } else if (choice.equals("2")){
            // This is where we get registration information
//            System.out.println("Please enter your First Name");
//            String first = sc.nextLine();
//            System.out.println("Please enter your Last Name");
//            String last = sc.nextLine();
//            System.out.println("Please enter your email");
//            String email = sc.nextLine();
//            System.out.println("Please enter your password");
//            String password = sc.nextLine();

            loggedInUser = us.register();

            // Now that we have all the information we need we can start talking to our TeacherService class to handle this info
        }else if (choice.equals("3")){
            us.getAllUsers();
        }

        if (loggedInUser != null){
            System.out.println("Press 1 to create a ticket, 2 to check status of existing ticket.");
            String subchoice = sc.nextLine();

            switch (subchoice){
                case "1":
                    rs.createTicket(loggedInUser);
                    break;
                case "2":
                    rs.getMyCurrentTickets(loggedInUser);
                    break;

                default:
                    System.out.println("invalid input");
            }

        }


    }
}