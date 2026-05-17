package com.anya.eventboard.model.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Participants {
    private int id;
    private int eventId;
    private String studentName;
    private String studentEmail;
}
