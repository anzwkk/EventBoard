package com.anya.eventboard.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Events {
    private int id;
    private String title;
    private LocalDateTime eventDate;
    private int maxSeats;

    public String getFormattedDate() {
        if (eventDate == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return eventDate.format(formatter);
    }


}
