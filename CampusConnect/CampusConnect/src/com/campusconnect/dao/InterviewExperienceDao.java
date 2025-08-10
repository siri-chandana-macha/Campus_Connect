package com.campusconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.campusconnect.connection.DbCon;
import com.campusconnect.model.InterviewExperience;

public class InterviewExperienceDao {
    public boolean submitExperience(InterviewExperience exp) {
        String sql = "INSERT INTO interview_experiences (name, roll_number, company, package_offered, duration, questions) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbCon.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exp.getName());
            ps.setString(2, exp.getRollNumber());
            ps.setString(3, exp.getCompany());
            ps.setString(4, exp.getPackageOffered());
            ps.setString(5, exp.getDuration());
            ps.setString(6, exp.getQuestions());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<InterviewExperience> getAllExperiences() throws SQLException, ClassNotFoundException {
        List<InterviewExperience> list = new ArrayList<>();
        String query = "SELECT * FROM interview_experiences ORDER BY submitted_at DESC";
        try (Connection conn = DbCon.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
        			ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            InterviewExperience ie = new InterviewExperience();
            ie.setRollNumber(rs.getString("roll_number"));
            ie.setName(rs.getString("name"));
            ie.setCompany(rs.getString("company"));
            ie.setPackageOffered(rs.getString("package_offered"));
            ie.setDuration(rs.getString("duration"));
            ie.setQuestions(rs.getString("questions"));
            list.add(ie);
        }
        }
        return list;
}
}

