package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.models.Users;
import com.revature.models.Reimbursement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOPostgres implements ReimbursementDAO{
    @Override
    public Reimbursement createReimbursement(Users user, Float cost, String description, String status) {
        Reimbursement ticket = new Reimbursement();
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO tickets (employee_id, cost, description, status) VALUES (?,?,?,?) RETURNING *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setFloat(2,cost);
            ps.setString(3, description);
            ps.setString(4, status);
            ResultSet rs;
            if((rs = ps.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("id");
                Float receivedCost = (rs.getFloat("cost"));
                String receivedDescription = rs.getString("description");
                String receivedStatus = rs.getString("status");
                ticket = new Reimbursement(id, receivedCost, receivedDescription, receivedStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
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
        List<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM tickets WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int employee_id = rs.getInt("employee_id");
                    Float cost = rs.getFloat("cost");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Reimbursement ticket = new Reimbursement(user, cost, description, status);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }


    @Override
    public Boolean updateTicket(Reimbursement reimbursement) {
        return null;
    }
}
