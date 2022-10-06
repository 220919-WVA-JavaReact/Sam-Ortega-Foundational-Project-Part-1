package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOCSV;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

import java.util.List;
import java.util.Scanner;

public class ReimbursementService {

    Scanner sc = new Scanner(System.in);

    ReimbursementDAO rd = new ReimbursementDAOPostgres();

    public Reimbursement createTicket(Users user){

        System.out.println("Enter amount: ");
        float cost = Float.parseFloat(sc.nextLine());

        System.out.println("Enter description: ");
        String description = sc.nextLine();

//        System.out.println("Are you a manager?");
        Boolean status = false;

        Reimbursement ticket = rd.createReimbursement(user, cost, description, status);
        return ticket;
    }

    public void getMyCurrentTickets(Users user){
        List<Reimbursement> tickets = rd.getMyCurrentTickets(user);
        if(tickets.size() > 0){
            for(Reimbursement ticket : tickets){
                System.out.println(ticket);
            }
        }else {
            System.out.println("You have no pending requests.");
        }
    }
}
