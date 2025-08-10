package com.campusconnect.servlets;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.campusconnect.dao.UserDao;
import com.campusconnect.model.User;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("loginSignup.jsp");
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String role = request.getParameter("role");
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");

        User user = new UserDao().validateUser(role,email, pwd);
        if (user != null) {
            request.getSession().setAttribute("rollNumber", user.getRollNumber());
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginSignup.jsp?error=true");
        }
    }
}