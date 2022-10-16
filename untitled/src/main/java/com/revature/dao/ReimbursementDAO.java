package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.models.Users;

import java.util.List;

public interface ReimbursementDAO {

    boolean createReimbursement(Reimbursement ticket, Users user);

    List<Reimbursement> getAllReimbursements();

    List<Reimbursement> getTicketByEmployeeId(int employee_id);

    List<Reimbursement> getMyCurrentTickets(Users user);
    Boolean updateTicket(Reimbursement reimbursement);

}