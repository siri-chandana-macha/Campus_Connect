package com.campusconnect.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.campusconnect.dao.PodcastDao;
import com.campusconnect.model.Podcast;

@WebServlet("/viewPodcasts")
public class ViewPodcastServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PodcastDao dao = new PodcastDao();
        List<Podcast> podcasts = dao.getAllPodcasts();

        request.setAttribute("podcastList", podcasts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("podcast.jsp");
        dispatcher.forward(request, response);
    }
}
