package com.campusconnect.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.campusconnect.dao.PodcastDao;
import com.campusconnect.model.Podcast;
@MultipartConfig
@WebServlet("/uploadPodcast")
public class UploadPodcastServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;

        if (!"admin".equals(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part filePart = request.getPart("file");

        if (title == null || title.isEmpty() || description == null || description.isEmpty() || filePart == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters.");
            return;
        }

        String contentDisp = filePart.getHeader("content-disposition");
        String fileName = contentDisp.substring(contentDisp.indexOf("filename=") + 10).replace("\"", "");
        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File is missing.");
            return;
        }

        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        fileName = new File(fileName).getName(); // Removes any path sent from browser
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        try (InputStream is = filePart.getInputStream();
             FileOutputStream fos = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        }

        Podcast podcast = new Podcast();
        podcast.setTitle(title);
        podcast.setDescription(description);
        podcast.setFilePath("uploads/" + fileName);

        PodcastDao dao = new PodcastDao();
        boolean success = dao.addPodcast(podcast);

        response.sendRedirect("viewPodcasts?status=" + (success ? "success" : "error"));
    }
}
