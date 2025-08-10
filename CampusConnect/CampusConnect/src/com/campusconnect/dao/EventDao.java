package com.campusconnect.dao;

import com.campusconnect.connection.DbCon;
import com.campusconnect.model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection conn = DbCon.getConnection()) {
            String query = "SELECT * FROM events ORDER BY date DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setTitle(rs.getString("title"));
                event.setOrganizer(rs.getString("organizer"));
                event.setDate(rs.getString("date"));
                event.setTime(rs.getString("time"));
                event.setVenue(rs.getString("venue"));
                event.setDescription(rs.getString("description"));
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public boolean addEvent(Event event) {
        boolean success = false;
        String query = "INSERT INTO events (title, organizer, date, time, venue, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbCon.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getOrganizer());
            ps.setString(3, event.getDate());
            ps.setString(4, event.getTime());
            ps.setString(5, event.getVenue());
            ps.setString(6, event.getDescription());

            int rows = ps.executeUpdate();
            success = rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
