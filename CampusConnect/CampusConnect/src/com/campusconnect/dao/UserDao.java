package com.campusconnect.dao;
import java.sql.*;
import com.campusconnect.connection.DbCon;
import com.campusconnect.model.User;

public class UserDao {

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (email, name, roll_number, pwd, branch, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbCon.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getRollNumber());
            ps.setString(4, user.getPwd());
            ps.setString(5, user.getBranch());
            ps.setString(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User validateUser(String role, String email, String pwd) {
        String sql = "SELECT * FROM users WHERE role = ? AND email = ? AND pwd = ?";
        try (Connection conn = DbCon.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	ps.setString(1, role);
            ps.setString(2, email);
            ps.setString(3, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setRollNumber(rs.getString("roll_number"));
                user.setBranch(rs.getString("branch"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static User getUserByRollNumber(String rollNumber) {
        User user = null;
        try (Connection conn = DbCon.getConnection()) {
            String sql = "SELECT * FROM users WHERE roll_number = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, rollNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setRollNumber(rs.getString("roll_number"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setBranch(rs.getString("branch"));
                user.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
