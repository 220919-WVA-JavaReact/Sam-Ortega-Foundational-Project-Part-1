package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.models.Users;

import java.util.ArrayList;
import java.util.List;

public interface ReimbursementDAO {

    boolean createReimbursement(Reimbursement ticket, Users user);
    ArrayList<Reimbursement> getAllPending(Users user);

    List<Reimbursement> getAllReimbursements();

    List<Reimbursement> getTicketByEmployeeId(int employee_id);

    List<Reimbursement> getMyCurrentTickets(Users user);
    Boolean updateTicket(Reimbursement ticket, String answer);

}