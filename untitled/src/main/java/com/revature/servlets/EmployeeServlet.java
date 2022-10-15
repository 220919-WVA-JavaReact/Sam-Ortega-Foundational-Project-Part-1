package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    UserService us = new UserService();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("login")){
            Users user = mapper.readValue(req.getInputStream(), Users.class); /*model.class*/
            Users use = us.login(user.getEmail(), user.getPassword());
            String respPayload = mapper.writeValueAsString(use);
            resp.getWriter().write(respPayload);
        } else if (req.getParameter("action").equals("register")) {
            Users employee = mapper.readValue(req.getInputStream(), Users.class);
            Users emp = us.register(employee.getFirst(), employee.getLast(), employee.getEmail(), employee.getPassword(), employee.getManager());
            String respPayload = mapper.writeValueAsString(emp);
            resp.getWriter().write(respPayload);
            resp.setStatus(204);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
