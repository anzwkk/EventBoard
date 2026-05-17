package com.anya.eventboard.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Events {
    private int id;
    private String title;
    private LocalDateTime eventDate;
    private int maxSeats;

}
