package com.esprit.microservice.gestionevent.Repositories;



import com.esprit.microservice.gestionevent.Entites.EtatEvent;
import com.esprit.microservice.gestionevent.Entites.Event;
import com.esprit.microservice.gestionevent.Entites.TypeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByType(TypeEvent type);
    List<Event> findByEtat(EtatEvent etat);
    List<Event> findByDateDebutBetween(LocalDate startDate, LocalDate endDate);

}