package com.anya.eventboard.model.service;

import com.anya.eventboard.model.entity.Events;
import com.anya.eventboard.model.entity.Participants;
import com.anya.eventboard.model.repository.EventRepo;
import com.anya.eventboard.model.repository.ParticipantRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepo eventRepo;

    @Mock
    private ParticipantRepo participantRepo;

    @InjectMocks
    private EventService eventService;

    @Test
    void registerParticipantSuccess() {
        Events fakeEvent = new Events();
        fakeEvent.setId(10);
        fakeEvent.setTitle("Java lesson");
        fakeEvent.setMaxSeats(5);

        Participants newPart = new Participants();
        newPart.setEventId(10);
        newPart.setStudentName("Анна");
        newPart.setStudentEmail("anna@gmail.com");

        when(eventRepo.findById(10)).thenReturn(fakeEvent);
        when(participantRepo.getLogedPartic(10)).thenReturn(new ArrayList<>());

        eventService.registerParticipant(newPart);

        verify(participantRepo, times(1)).addNewPartic(newPart);
    }

    @Test
    void registerParticipantEventNotFound(){
        Participants newPart = new Participants();
        newPart.setEventId(92342);

        when(eventRepo.findById(92342)).thenReturn(null);

        try {
            eventService.registerParticipant(newPart);
            fail("Метод должен был выбросить ошибку");
        } catch (RuntimeException exception) {
            assertEquals("Захід з таким айді немає", exception.getMessage());
        }
        verify(participantRepo, never()).addNewPartic(any());
    }

    @Test
    void registerParticipantNoAvailableSeats() {
        Events fakeEvent = new Events();
        fakeEvent.setId(10);
        fakeEvent.setMaxSeats(1);

        Participants newPart = new Participants();
        newPart.setEventId(10);

        ArrayList<Participants> busySeats = new ArrayList<>();
        busySeats.add(new Participants());

        when(eventRepo.findById(10)).thenReturn(fakeEvent);
        when(participantRepo.getLogedPartic(10)).thenReturn(busySeats);

        try {
            eventService.registerParticipant(newPart);
            fail("Метод должен был выбросить ошибку");
        } catch (RuntimeException exception) {
            assertEquals("Немає вільних місць на цей захід", exception.getMessage());
        }
        verify(participantRepo, never()).addNewPartic(any());
    }

    @Test
    void avaliableSeatsSuccess() {
        Events event = new Events();
        event.setId(2);
        event.setMaxSeats(10);

        ArrayList<Participants> busySeats = new ArrayList<>();
        busySeats.add(new Participants());
        busySeats.add(new Participants());
        busySeats.add(new Participants());

        when(eventRepo.findById(2)).thenReturn(event);
        when(participantRepo.getLogedPartic(2)).thenReturn(busySeats);

        int result = eventService.avaliableSeats(2);
        assertEquals(7, result);
    }

    @Test
    void avaliableSeatNotFound(){
        when(eventRepo.findById(934324)).thenReturn(null);

        int result = eventService.avaliableSeats(934324);

        assertEquals(0, result);
    }
}