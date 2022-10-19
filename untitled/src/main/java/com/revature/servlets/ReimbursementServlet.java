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
import java.util.HashMap;
import java.util.List;

@WebServlet("/tickets")
public class ReimbursementServlet extends HttpServlet {
    UserService es = new UserService();
    ReimbursementService rs = new ReimbursementService();
    ReimbursementDAOPostgres rd = new ReimbursementDAOPostgres();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
//        Reimbursement ticket = mapper.readValue(req.getInputStream(), Reimbursement.class);
//        float cost = ticket.getCost();
//        String description = ticket.getDescription();
//        String status = ticket.getStatus();

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

//            resp.getWriter().write(mapper.writeValueAsString(loggedInEmployee));
            //we now have the sessions info, now to call for ticket
            ReimbursementService rs = new ReimbursementService();
            List<Reimbursement> tickets = rs.getMyCurrentTickets(loggedInEmployee);
            if (tickets != null) {
                resp.setStatus(200);
                resp.getWriter().write(mapper.writeValueAsString(loggedInEmployee.getFirst()+"'s" + " tickets!"));
                resp.getWriter().write(mapper.writeValueAsString("<br>"));
                for(Reimbursement ticket: tickets){

                    resp.getWriter().write(mapper.writeValueAsString("Ticket ID: "+ticket.getId())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Cost: "+ticket.getCost())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Description: "+ticket.getDescription())+" ");
                    resp.getWriter().write(mapper.writeValueAsString("Status: "+ticket.getStatus()) + ", ");
                    resp.getWriter().write("<br>");
                }
//                resp.getWriter().write(mapper.writeValueAsString(ticket.getStatus()));
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);

        if(session == null){
            resp.setStatus(400);
            resp.setContentType("application/json");
            HashMap<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("Status code", 400);
            errorMessage.put("Message", "User not found.");

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
            return;
        }else{
            Users loggedInUser = (Users) session.getAttribute("auth-user");
//            resp.getWriter().write(mapper.writeValueAsString(loggedInUser));

            Reimbursement ticket = mapper.readValue(req.getInputStream(), Reimbursement.class);
            resp.setStatus(201);
            float cost = ticket.getCost();
            String description = ticket.getDescription();
//            boolean succeeded = rs.createTicket(cost, description, loggedInUser);
            boolean succeeded;
//            System.out.println(succeeded);

            if(cost != 0 && !description.equals("")){
                succeeded = rs.createTicket(cost, description, loggedInUser);
                resp.setStatus(201);
                resp.getWriter().write("Ticket submission successful!");
            }else{
                resp.setStatus(400);
                resp.getWriter().write("Unsuccessful. Please try again.");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
