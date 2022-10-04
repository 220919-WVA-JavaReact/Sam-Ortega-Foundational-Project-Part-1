package com.revature.service;

import com.revature.dao.ReimbursementDAOCSV;
import com.revature.models.Reimbursement;

import java.util.Scanner;

public class ReimbursementService {

    Scanner sc = new Scanner(System.in);

    ReimbursementDAOCSV rd = new ReimbursementDAOCSV();

    public void createTicket(){
        System.out.println("Enter employee ID: ");
        int employee = Integer.parseInt(sc.nextLine());

        System.out.println("Enter amount: ");
        float cost = Float.parseFloat(sc.nextLine());

        System.out.println("Enter description: ");
        String description = sc.nextLine();

        System.out.println("Are you a manager?");
        Boolean isManager = Boolean.valueOf(sc.nextLine());

        rd.createReimbursement(employee, cost, description, isManager);
    }

    public void assignTicket(int employee_id) {
        System.out.println("Enter ticket number: ");
        int ticketNumber = sc.nextInt();

        Reimbursement assign = new Reimbursement(ticketNumber, employee_id);

        rd.updateTicket(assign);
    }
}
