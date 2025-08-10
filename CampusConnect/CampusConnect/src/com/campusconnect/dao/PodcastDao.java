package com.campusconnect.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.campusconnect.model.Podcast;
import com.campusconnect.connection.DbCon;

public class PodcastDao {
    public boolean addPodcast(Podcast podcast) {
        String query = "INSERT INTO podcasts (title, description, file_path) VALUES (?, ?, ?)";
        try (Connection conn = DbCon.getConnection();
        		PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, podcast.getTitle());
            ps.setString(2, podcast.getDescription());
            ps.setString(3, podcast.getFilePath());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Podcast> getAllPodcasts() {
        List<Podcast> list = new ArrayList<>();
        String query = "SELECT * FROM podcasts ORDER BY id DESC";
        try (Connection conn = DbCon.getConnection();
        		PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Podcast p = new Podcast();
                p.setId(rs.getInt("id"));
                p.setTitle(rs.getString("title"));
                p.setDescription(rs.getString("description"));
                p.setFilePath(rs.getString("file_path"));
                p.setUploadTime(rs.getTimestamp("upload_time"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
