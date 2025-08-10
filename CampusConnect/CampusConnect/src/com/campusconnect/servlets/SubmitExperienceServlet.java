package com.campusconnect.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import com.campusconnect.dao.InterviewExperienceDao;
import com.campusconnect.model.InterviewExperience;

@WebServlet("/submitExperience")
public class SubmitExperienceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InterviewExperience exp = new InterviewExperience();
        exp.setName(request.getParameter("name"));
        exp.setRollNumber(request.getParameter("rollNumber"));
        exp.setCompany(request.getParameter("company"));
        exp.setPackageOffered(request.getParameter("package"));
        exp.setDuration(request.getParameter("duration"));
        exp.setQuestions(request.getParameter("questions"));

        InterviewExperienceDao dao = new InterviewExperienceDao();
        if (dao.submitExperience(exp)) {
            response.sendRedirect("interviewExp.jsp?submitted=true");
        } else {
            response.sendRedirect("interviewExp.jsp?error=true");
        }
    }
}
