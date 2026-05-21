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
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Participant", value = "/event")
public class ParticipantServlet extends HttpServlet{
    private EventRepo eventRepo;
    private ParticipantRepo participantRepo;
    private EventService eventService;

    public void init() throws ServletException{
        this.eventRepo = new EventRepo();
        this.participantRepo = new ParticipantRepo();
        this.eventService = new EventService(eventRepo, participantRepo);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int eventId = Integer.parseInt(req.getParameter("id"));
        Events event = eventRepo.findById(eventId);
        ArrayList<Participants> participants = participantRepo.getLogedPartic(eventId);

        req.setAttribute("selectedEvent", event);
        req.setAttribute("participantsList", participants);
        req.setAttribute("eventService", eventService);
        req.getRequestDispatcher("/view/event-details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException{
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        String name = req.getParameter("studentName");
        String email = req.getParameter("studentEmail");

        Participants newParticipant = new Participants();
        newParticipant.setStudentName(name);
        newParticipant.setStudentEmail(email);
        newParticipant.setEventId(eventId);

        try{
            eventService.registerParticipant(newParticipant);
            resp.sendRedirect(req.getContextPath() + "/event?id=" + eventId);
        } catch (RuntimeException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<h2 style='color: red;'>Помилка реєстрації: " + e.getMessage() + "</h2>");
        }

    }


}
