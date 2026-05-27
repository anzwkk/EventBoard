package com.anya.eventboard.model.service;
import com.anya.eventboard.model.entity.Events;
import com.anya.eventboard.model.entity.Participants;
import com.anya.eventboard.model.repository.EventRepo;
import com.anya.eventboard.model.repository.ParticipantRepo;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor

public class EventService {
    private final EventRepo eventRepo;
    private final ParticipantRepo participantRepo;

    public void registerParticipant(Participants part){
        int idOfEvent = part.getEventId();
        Events event = eventRepo.findById(idOfEvent);
        if (event == null) {
            throw new RuntimeException("Захід з таким айді немає");
        }

        int maxSeats = event.getMaxSeats();

        ArrayList<Participants> alredyReg = participantRepo.getLogedPartic(idOfEvent);
        int busySeats = alredyReg.size();
        if (busySeats >= maxSeats){
            throw new RuntimeException("Немає вільних місць на цей захід");
        }
        participantRepo.addNewPartic(part);


    }

    public int avaliableSeats(int eventId){
        Events event = eventRepo.findById(eventId);
        if (event == null) {
            return 0;
        }
        int alreadyReg = participantRepo.getLogedPartic(eventId).size();
        return event.getMaxSeats() - alreadyReg;
    }


}
