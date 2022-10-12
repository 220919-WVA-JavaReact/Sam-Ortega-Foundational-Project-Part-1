package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.models.Users;
import java.util.List;

public class ReimbursementDAOCSV implements ReimbursementDAO{

    @Override
    public Reimbursement createReimbursement(Users user, Float cost, String description, String status) {
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
    public List<Reimbursement> getMyCurrentTickets(Users user) {
        return null;
    }

    @Override
    public Boolean updateTicket(Reimbursement reimbursement) {
        return false;
    }
}
