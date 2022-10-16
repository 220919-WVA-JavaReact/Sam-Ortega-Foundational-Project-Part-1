package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDAOPostgres;
import com.revature.models.Reimbursement;
import com.revature.models.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
@WebServlet("/tickets")
public class ReimbursementServlet extends HttpServlet {
    ReimbursementDAOPostgres rd = new ReimbursementDAOPostgres();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
            resp.getWriter().write(mapper.writeValueAsString(loggedInUser));

            Reimbursement ticket = mapper.readValue(req.getInputStream(), Reimbursement.class);
            resp.setStatus(200);
            float cost = (float)ticket.getCost();
            String description = (String) ticket.getDescription();
            boolean succeeded = rd.createReimbursement(cost, description, loggedInUser);
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
