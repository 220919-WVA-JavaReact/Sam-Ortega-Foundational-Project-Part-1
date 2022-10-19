package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReimbursementService {

    public ReimbursementService(){
        System.out.println("Reimbursements Service Initialized!");
    }

    Scanner sc = new Scanner(System.in);

    ReimbursementDAO rd = new ReimbursementDAOPostgres();

    public boolean createTicket(float amount, String description, Users user){

//        System.out.println("Enter amount: ");
//        float cost = Float.parseFloat(sc.nextLine());
//
//        System.out.println("Enter description: ");
//        String description = sc.nextLine();

//        System.out.println("Are you a manager?");
//        String status = "pending";
        Reimbursement ticket = null;
        ticket = new Reimbursement(user, amount, description);
        boolean didWork = rd.createReimbursement(ticket, user);
//        System.out.println(didWork);
        return didWork;
    }

    public List<Reimbursement> getMyCurrentTickets(Users user){
        List<Reimbursement> tickets = rd.getMyCurrentTickets(user);

//        if(tickets.size() > 0){
//            for(Reimbursement ticket : tickets){
//                System.out.println(ticket);
//            }
//        }else {
//            System.out.println("You have no pending requests.");
//        }
        return tickets;
    }

    public List<Reimbursement> getAllPendingTickets(){
        Users user = new Users();
        List<Reimbursement> tickets = rd.getAllPending(user);

//        if(tickets.size() > 0){
//            for(Reimbursement ticket : tickets){
//                System.out.println(ticket);
//            }
//        }else {
//            System.out.println("You have no pending requests.");
//        }
        return tickets;
    }

    public boolean updateTickets(Reimbursement ticket, String status){
//        Reimbursement ticket = new Reimbursement(id);
//        System.out.println("ticket in reimb service "+ticket);
        boolean updated = rd.updateTicket(ticket, status);
//        System.out.println("int reimbursement service " + updated);
        if(updated){
            return true;
        }
        return false;
    }
}
