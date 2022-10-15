package com.revature.servlets;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOPostgres;
import com.revature.models.Users;
import com.revature.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    UserDAO userD = (UserDAO) new UserDAOPostgres();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().write("<h1>test works!</h1>");
        resp.getWriter().write("<form action=\"login\" method=\"post\">");
        resp.getWriter().write("<label for=\"email\">Email:</label>");
        resp.getWriter().write("<input name=\"email\" size=\"30\" />");
        resp.getWriter().write("<br><br>");
        resp.getWriter().write("<label for=\"password\">Password:</label>");
        resp.getWriter().write("<input type=\"password\" name=\"password\" size=\"30\" />");
        resp.getWriter().write("<br><br>  ");
        resp.getWriter().write("<button type=\"submit\">Login</button>");
        resp.getWriter().write("</form>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();

        String e = req.getHeader("email");
        String p = req.getHeader("password");

        if(userD.checkLogin(e,p)){
//            RequestDispatcher rd = req.getRequestDispatcher("servlet2");
//            rd.include(req,resp);
            resp.getWriter().write("this is a test!");
        }
    }
}
