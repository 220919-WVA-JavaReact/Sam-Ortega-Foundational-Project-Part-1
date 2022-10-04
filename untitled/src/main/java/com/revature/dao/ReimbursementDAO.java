package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public interface ReimbursementDAO {

    Reimbursement createReimbursement(int employee_id, float cost, String description, Boolean status);

    List<Reimbursement> getAllReimbursements();

    List<Reimbursement> getTicketByEmployeeId(int employee_id);

    Boolean updateTicket(Reimbursement reimbursement);

}