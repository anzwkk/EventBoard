package com.anya.eventboard.model.repository;

import com.anya.eventboard.db.DatabaseConnection;
import com.anya.eventboard.model.entity.Participants;

import java.sql.*;
import java.util.ArrayList;

public class ParticipantRepo {
    public ArrayList<Participants> getLogedPartic(int eventId) {
        ArrayList<Participants> partic = new ArrayList<>();
        String sql = "SELECT id, event_id, student_name, student_email FROM participants WHERE event_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Participants part = new Participants();
                    part.setEventId(rs.getInt("event_id"));
                    part.setStudentName(rs.getString("student_name"));
                    part.setStudentEmail(rs.getString("student_email"));


                    partic.add(part);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partic;
    }

    public void addNewPartic(Participants partic){
        String sql = "INSERT INTO participants (student_name, student_email, event_id) VALUES (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, partic.getStudentName());
            pstmt.setString(2, partic.getStudentEmail());
            pstmt.setInt(3, partic.getEventId());

            pstmt.executeUpdate();
            System.out.println("student zaregan");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
