package com.anya.eventboard.controller.servlet;

import com.anya.eventboard.model.entity.Events;
import com.anya.eventboard.model.entity.Participants;
import com.anya.eventboard.model.repository.EventRepo;
import com.anya.eventboard.model.repository.ParticipantRepo;
import com.anya.eventboard.model.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "Events", value = "/events")
public class EventServlet extends HttpServlet {
    private EventRepo eventRepo;
    private EventService eventService;

    public void init() throws ServletException {
        this.eventRepo = new EventRepo();
        ParticipantRepo participantRepo = new ParticipantRepo();
        this.eventService = new EventService(eventRepo, participantRepo);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        ArrayList<Events> events = eventRepo.findAll();

        req.setAttribute("eventsList", events);;
        req.setAttribute("eventService", eventService);

        req.getRequestDispatcher("/view/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        String title = req.getParameter("title");
        String date = req.getParameter("eventDate");
        int maxSeats = Integer.parseInt(req.getParameter("maxSeats"));

        Events newEvent = new Events();
        newEvent.setTitle(title);
        newEvent.setEventDate(LocalDateTime.parse(date));
        newEvent.setMaxSeats(maxSeats);

        eventRepo.addNewEvent(newEvent);

        resp.sendRedirect(req.getContextPath() + "/events");
    }

}
