package com.example.mynetworkapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static boolean insertUser(String name, String email, String password) throws SQLException {
        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        }
        return null;
    }

    public static int getSkillIdByName(String skillName) throws SQLException {
        String query = "SELECT id FROM skills WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, skillName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        return -1; // not found
    }

    public static void insertUserSkill(int userId, int skillId) throws SQLException {
        String query = "INSERT INTO user_skills (user_id, skill_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, skillId);
            stmt.executeUpdate();
        }
    }

    public static void clearUserSkills(int userId) throws SQLException {
        String query = "DELETE FROM user_skills WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }
    //clearUserSkills
    //clearUserSocialLinks

    public static void clearUserSocialLinks(int userId) throws SQLException {
        String query = "DELETE FROM social_links WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    public static void insertSocialLink(int userId, String platform, String url) throws SQLException {
        String query = "INSERT INTO social_links (user_id, platform, url) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, platform);
            stmt.setString(3, url);
            stmt.executeUpdate();
        }
    }

    public static List<String> getsociallink(int userId) throws SQLException {
        String query = "SELECT * FROM social_links WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            List<String> socialLinks = new ArrayList<>();
            while (rs.next()) {
                String platform = rs.getString("platform");
                String url = rs.getString("url");
                socialLinks.add(platform + ": " + url);
            }
            return socialLinks;
        }
    }

    public static List<User> findUsersWithSkills(List<String> skills) throws SQLException {
        // Build the query
        StringBuilder sql = new StringBuilder("SELECT DISTINCT u.* FROM users u");

        for (int i = 0; i < skills.size(); i++) {
            sql.append(" JOIN user_skills us").append(i)
                    .append(" ON u.id = us").append(i).append(".user_id")
                    .append(" JOIN skills s").append(i)
                    .append(" ON us").append(i).append(".skill_id = s").append(i).append(".id")
                    .append(" AND s").append(i).append(".name = ?");
        }

        List<User> users = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Set parameters
            for (int i = 0; i < skills.size(); i++) {
                stmt.setString(i + 1, skills.get(i));
            }

            // Execute query and process results
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    ));
                }
            }
        }

        return users;
    }

    public static List<String> getskills(int id) {
        String query = "SELECT s.name FROM skills s " +
                "JOIN user_skills us ON s.id = us.skill_id " +
                "WHERE us.user_id = ?";
        List<String> skills = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnectionconnect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                skills.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
}

