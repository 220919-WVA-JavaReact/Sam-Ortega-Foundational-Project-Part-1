package com.revature.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was initialized at: " + LocalDateTime.now());
//        ObjectMapper mapper = new ObjectMapper();
//        EmployeeServlet es = new EmployeeServlet(mapper);
//        ServletContext context = sce.getServletContext();
//        ServletRegistration.Dynamic registeredServlet = context.addServlet("EmployeeServlet", es);
//        registeredServlet.addMapping("/employees");
//        registeredServlet.setLoadOnStartup(3);
//        registeredServlet.setInitParameter("employee-servlet-key", "user-servlet-value");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at: " + LocalDateTime.now());
    }
}