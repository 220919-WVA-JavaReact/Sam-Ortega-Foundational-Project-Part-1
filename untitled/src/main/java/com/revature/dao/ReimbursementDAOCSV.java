package com.revature.dao;

import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementDAOCSV implements ReimbursementDAO{

    @Override
    public Reimbursement createReimbursement(int employee_id, float cost, String description, Boolean status) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursements() {
        return null;
    }

    @Override
    public List<Reimbursement> getTicketByEmployeeId(int employee_id) {
        return null;
    }

    @Override
    public Boolean updateTicket(Reimbursement reimbursement) {
        return false;
    }
}
