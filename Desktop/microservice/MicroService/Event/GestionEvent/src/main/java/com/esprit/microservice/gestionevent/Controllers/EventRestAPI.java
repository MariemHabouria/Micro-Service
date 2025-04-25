package com.esprit.microservice.gestionevent.Controllers;

import com.esprit.microservice.gestionevent.Entites.EtatEvent;
import com.esprit.microservice.gestionevent.Entites.Event;
import com.esprit.microservice.gestionevent.Entites.TypeEvent;
import com.esprit.microservice.gestionevent.Services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventRestAPI {

    private final EventService eventService;

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @PostMapping("/user")
    public ResponseEntity<Event> creerEvent(@RequestBody Event event) {
        Event newEvent = eventService.creerEvent(event);
        return ResponseEntity.ok(newEvent);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/user/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/user/debug/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/user/type/{type}")
    public ResponseEntity<List<Event>> getEventsByType(@PathVariable TypeEvent type) {
        return ResponseEntity.ok(eventService.getEventsByType(type));
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/user/etat/{etat}")
    public ResponseEntity<List<Event>> getEventsByEtat(@PathVariable EtatEvent etat) {
        return ResponseEntity.ok(eventService.getEventsByEtat(etat));
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/user/between")
    public ResponseEntity<List<Event>> getEventsBetweenDates(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<Event> events = eventService.getEventsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(events);
    }
}
