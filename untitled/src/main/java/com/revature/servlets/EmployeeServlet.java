package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOPostgres;
import com.revature.models.Users;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    UserDAO ud = (UserDAO) new UserDAOPostgres();
    UserService us = new UserService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if (req.getParameter("action").equals("login")){
            Users user = mapper.readValue(req.getInputStream(), Users.class);
            Users use = us.login(user.getEmail(), user.getPassword());
            String respPayload = mapper.writeValueAsString(use);
            if(respPayload.equals("null")){
                resp.getWriter().write("Wrong Credentials - Try Again!");

            }else{
                session = req.getSession();
                session.setAttribute("auth-user", use);
                resp.setStatus(200);
                resp.getWriter().write("Welcome back " +use.getFirst() + "!");
//                resp.getWriter().write(respPayload);
            }

        } else if (req.getParameter("action").equals("register")) {
            Users employee = mapper.readValue(req.getInputStream(), Users.class);

                Users emp = us.register(employee.getFirst(), employee.getLast(), employee.getEmail(), employee.getPassword(), employee.getManager());
                String respPayload = mapper.writeValueAsString(emp);
                if(!respPayload.equals("null")){
                    resp.setStatus(201);
                    resp.getWriter().write(emp.getFirst() + " Registered Successfully!");
                }else{
                    resp.setStatus(400);
                    resp.getWriter().write("That EMAIL has already been taken!");
                }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            resp.getWriter().write("session ended");
        }
    }
}
