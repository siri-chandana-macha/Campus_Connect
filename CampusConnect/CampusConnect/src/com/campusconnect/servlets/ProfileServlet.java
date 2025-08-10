package com.campusconnect.servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.campusconnect.dao.UserDao;
import com.campusconnect.model.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollNumber = (String) request.getSession().getAttribute("rollNumber");

        if (rollNumber == null) {
            response.sendRedirect("loginSignup.jsp");
            return;
        }

        User user = UserDao.getUserByRollNumber(rollNumber);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
        dispatcher.forward(request, response);
    }
}
