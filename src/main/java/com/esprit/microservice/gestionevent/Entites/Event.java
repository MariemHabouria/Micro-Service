package com.esprit.microservice.gestionevent.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Name of the event (e.g., Yoga Class)

    private String description;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private int capacity; // Number of participants allowed

    @Enumerated(EnumType.STRING)
    private TypeEvent type; // SPORTIF, WORKSHOP, COMPETITION

    @Enumerated(EnumType.STRING)
    private EtatEvent etat; // OUVERT, COMPLET, ANNULE
}
