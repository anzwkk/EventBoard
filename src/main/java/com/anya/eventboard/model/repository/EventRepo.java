package com.anya.eventboard.model.repository;
import com.anya.eventboard.db.DatabaseConnection;
import com.anya.eventboard.model.entity.Events;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EventRepo {
    public ArrayList<Events> findAll() {
        ArrayList<Events> events = new ArrayList<>();
        String sql = "SELECT id, title, event_date, max_seats FROM events";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()){
                Events event = new Events();
                        event.setId(rs.getInt("id"));
                        event.setTitle(rs.getString("title"));
                        event.setMaxSeats(rs.getInt("max_seats"));
                        event.setEventDate(rs.getTimestamp("event_date").toLocalDateTime());
                events.add(event);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public Events findById(int id){
        String sql = "SELECT id, title, event_date, max_seats FROM events WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

             pstmt.setInt(1, id);
           try(ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   Events event = new Events();

                   event.setId(rs.getInt("id"));
                   event.setTitle(rs.getString("title"));
                   event.setMaxSeats(rs.getInt("max_seats"));
                   event.setEventDate(rs.getTimestamp("event_date").toLocalDateTime());

                   return event;
               }
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}



