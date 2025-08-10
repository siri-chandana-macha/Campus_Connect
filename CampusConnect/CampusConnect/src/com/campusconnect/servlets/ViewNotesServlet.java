package com.campusconnect.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.campusconnect.dao.NotesDao;
import com.campusconnect.model.Notes;

@WebServlet("/viewNotes")
public class ViewNotesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        NotesDao dao = new NotesDao();
        List<Notes> notes = dao.getAllNotes();

        request.setAttribute("notesList", notes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("notes.jsp");
        dispatcher.forward(request, response);
    }
}
