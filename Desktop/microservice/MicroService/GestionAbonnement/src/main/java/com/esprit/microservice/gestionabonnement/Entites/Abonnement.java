package com.esprit.microservice.gestionabonnement.Entites;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.*;
@Entity
@Table(name = "abonnements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Abonnement implements Serializable  {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

        private String userId; // ID de l'utilisateur récupéré depuis Keycloak

        @Enumerated(EnumType.STRING)
        private TypeAbonnement type;

        private LocalDate dateDebut;
        private LocalDate dateFin;

        private double prix;

        @Enumerated(EnumType.STRING)
        private StatutAbonnement statut; // ACTIF, EXPIRE, ANNULE
}