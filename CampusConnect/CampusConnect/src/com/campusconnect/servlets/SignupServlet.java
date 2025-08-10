package com.campusconnect.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.campusconnect.dao.UserDao;
import com.campusconnect.model.User;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles GET request (like when you visit /signup directly in browser)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("loginSignup.jsp"); // or the JSP page that has your signup form
    }

    // Handles POST request (form submission)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPwd(request.getParameter("password"));
        user.setRollNumber(request.getParameter("rollNumber"));
        user.setBranch(request.getParameter("branch"));
        user.setRole(request.getParameter("role"));

        boolean status = new UserDao().registerUser(user);
        if (status) {
            response.sendRedirect("loginSignup.jsp");
        } else {
            response.sendRedirect("loginSignup.jsp?error=true");
        }
    }
}
