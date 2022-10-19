package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    UserService es = new UserService();
    ReimbursementService rs = new ReimbursementService();
    ReimbursementDAOPostgres rd = new ReimbursementDAOPostgres();
    ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.setStatus(400);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "No user found with provided credentials");

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        } else {
            Users loggedInEmployee = (Users) session.getAttribute("auth-user");
            ReimbursementService rs = new ReimbursementService();
            List<Reimbursement> tickets = rs.getAllPendingTickets();
            if (loggedInEmployee.getManager()) {
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString("List of all pending tickets!"));
                resp.getWriter().write(mapper.writeValueAsString("<br>"));
//                resp.getWriter().write(mapper.writeValueAsString(tickets));
                for(Reimbursement ticket: tickets){
//                    resp.getWriter().write(mapper.writeValueAsString("First name: "+ticket.getUser().getFirst())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Ticket ID: " +ticket.getId())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Cost: "+ticket.getCost())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Description: "+ticket.getDescription())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Status: "+ticket.getStatus()) + ", ");
                    resp.getWriter().write("<br>");
//                    resp.getWriter().write(mapper.writeValueAsString(ticket.getUser().getFirst()));
                }
//                resp.getWriter().write(mapper.writeValueAsString(ticket.getStatus()));
            }else{
                resp.getWriter().write("Must be a Manager to view all employee's pending tickets.");
            }

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(400);
            resp.setContentType("application/json");

            HashMap<String, Object> errorMessage = new HashMap<>();

            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "No user found with provided credentials");

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;

        }else {
            Users loggedInEmployee = (Users) session.getAttribute("auth-user");
            Reimbursement ticket = mapper.readValue(req.getInputStream(), Reimbursement.class);
            String ticketStatus = ticket.getStatus();
            if (loggedInEmployee.getManager().equals(false)) {
                resp.setStatus(400);
                resp.getWriter().write("Employees may not Approve or Deny Tickets.");
                return;
            } else {
                if (req.getParameter("action").equals("approve")) {
                    boolean approved = rs.updateTickets(ticket, ticketStatus);
//                    System.out.println("in the servlet "+approved);
//                    System.out.println(ticket);
                    if (approved) {
                        resp.setStatus(200);
                        resp.getWriter().write("Ticket has been approved.");
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("Ticket cannot be updated.");
                    }

                } else if (req.getParameter("action").equals("deny")) {
                    boolean denied = rs.updateTickets(ticket, ticketStatus);
                    if (denied) {
                        resp.setStatus(200);
                        resp.getWriter().write("Ticket has been denied.");
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("Ticket cannot be updated.");
                    }
                }
            }
        }
    }
}
