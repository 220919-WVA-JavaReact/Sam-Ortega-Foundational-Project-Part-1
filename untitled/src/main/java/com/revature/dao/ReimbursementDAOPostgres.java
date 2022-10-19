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
    private Users user;

    @Override
    public boolean createReimbursement(Reimbursement ticket,Users user) {
//        Reimbursement ticket = new Reimbursement();
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO tickets (employee_id, cost, description) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setFloat(2,ticket.getCost());
            ps.setString(3, ticket.getDescription());
//            ps.setString(4, "");

            int updated = ps.executeUpdate();
//            System.out.println(updated);

            if(updated == 1){

                return true;
            }

//            if((rs = ps.executeQuery()) != null){
//                rs.next();
//                int id = rs.getInt("id");
//                Float receivedCost = (rs.getFloat("cost"));
//                String receivedDescription = rs.getString("description");
//                String receivedStatus = rs.getString("status");
//                ticket = new Reimbursement(id, receivedCost, receivedDescription, receivedStatus);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Reimbursement> getAllPending(Users user) {
        ArrayList<Reimbursement> tickets = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM tickets WHERE status = 'pending'";
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, "pending");
            ResultSet rs;
            if ((rs = ps.executeQuery()) != null) {
                while (rs.next()) {
                    int employee_id = rs.getInt("employee_id");
                    int id = rs.getInt("id");
                    Float cost = rs.getFloat("cost");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Reimbursement ticket = new Reimbursement(id,cost, description, status);
//                    System.out.println(user);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

//    @Override
//    public boolean createReimbursement(Reimbursement ticket, Users user) {
//        return false;
//    }

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
    public Boolean updateTicket(Reimbursement ticket, String answer) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "UPDATE tickets SET status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, answer);
            ps.setInt(2, ticket.getId());

            System.out.println("ticket in dao " +ticket);
            System.out.println("answer in dao "+answer);

            int result = ps.executeUpdate();

            System.out.println("in the dao " + result);
            if (result == 1) {

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong in query!");
            e.printStackTrace();
        }
        return false;
    }
}
