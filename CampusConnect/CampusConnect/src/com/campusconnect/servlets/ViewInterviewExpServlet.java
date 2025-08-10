package com.campusconnect.servlets;

import com.campusconnect.connection.DbCon;
import com.campusconnect.dao.InterviewExperienceDao;
import com.campusconnect.model.InterviewExperience;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
@WebServlet("/interviewExp")
public class ViewInterviewExpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection con = DbCon.getConnection()) {
            InterviewExperienceDao dao = new InterviewExperienceDao();
            List<InterviewExperience> experiences = dao.getAllExperiences();
            request.setAttribute("experiences", experiences);
            RequestDispatcher rd = request.getRequestDispatcher("viewInterviewExp.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
