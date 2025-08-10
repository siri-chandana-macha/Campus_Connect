package com.campusconnect.servlets;

import com.campusconnect.connection.DbCon;
import com.campusconnect.dao.EventDao;
import com.campusconnect.model.Event;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/uploadEvent")
public class UploadEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("title");
        String organizer = request.getParameter("organizer");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String venue = request.getParameter("venue");
        String description = request.getParameter("description");

        try (Connection conn = DbCon.getConnection();){
            String query = "INSERT INTO events (title, organizer, date, time, venue, description) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, organizer);
            ps.setString(3, date);
            ps.setString(4, time);
            ps.setString(5, venue);
            ps.setString(6, description);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("ViewEventServlet");  // Forward to view all events
            } else {
                response.getWriter().println("Event upload failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
