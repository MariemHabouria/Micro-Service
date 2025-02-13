package com.esprit.microservice.gestionabonnement.Repositories;

import com.esprit.microservice.gestionabonnement.Entites.Abonnement;
import com.esprit.microservice.gestionabonnement.Entites.StatutAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    // Trouver un abonnement par userId (Keycloak)
    List<Abonnement> findByUserId(String userId);

    // Trouver un abonnement actif d'un utilisateur
    Optional<Abonnement> findByUserIdAndStatut(String userId, StatutAbonnement statut);
}
