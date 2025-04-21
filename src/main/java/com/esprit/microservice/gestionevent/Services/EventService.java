package com.esprit.microservice.gestionevent.Services;


import com.esprit.microservice.gestionevent.Entites.EtatEvent;
import com.esprit.microservice.gestionevent.Entites.Event;
import com.esprit.microservice.gestionevent.Entites.TypeEvent;
import com.esprit.microservice.gestionevent.Repositories.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    public Event creerEvent(Event event) {
        event.setEtat(EtatEvent.OUVERT);
        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByType(TypeEvent type) {
        return eventRepository.findByType(type);
    }

    public List<Event> getEventsByEtat(EtatEvent etat) {
        return eventRepository.findByEtat(etat);
    }

    public boolean deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Event> getEventsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByDateDebutBetween(startDate, endDate);
    }

}