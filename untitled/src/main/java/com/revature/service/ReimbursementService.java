package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

import java.util.List;
import java.util.Scanner;

public class ReimbursementService {

    Scanner sc = new Scanner(System.in);

    ReimbursementDAO rd = new ReimbursementDAOPostgres();

    public Reimbursement createTicket(float amount, String description, String status, Users user){

//        System.out.println("Enter amount: ");
//        float cost = Float.parseFloat(sc.nextLine());
//
//        System.out.println("Enter description: ");
//        String description = sc.nextLine();

//        System.out.println("Are you a manager?");
//        String status = "pending";
        Reimbursement ticket = null;
        ticket = new Reimbursement(amount, description, status, user);
        boolean didWork = rd.createReimbursement(ticket, user);
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
