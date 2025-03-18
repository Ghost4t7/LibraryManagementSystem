package org.aniket.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static void registerUser(String name, String email, String password, String role, String phone) {
        String query = "INSERT INTO users (name, email, password, role, phone) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.setString(5, phone);
            
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        try (Connection con = LMS.dbConnect();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

