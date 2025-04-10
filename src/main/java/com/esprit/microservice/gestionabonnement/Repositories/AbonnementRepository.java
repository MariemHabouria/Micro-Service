package com.esprit.microservice.gestionabonnement.Repositories;

import com.esprit.microservice.gestionabonnement.Entites.Abonnement;
import com.esprit.microservice.gestionabonnement.Entites.StatutAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    // Métiers de base (existants)
    List<Abonnement> findByUserId(String userId);
    Optional<Abonnement> findByUserIdAndStatut(String userId, StatutAbonnement statut);

    // === Métiers avancés ===

    // 1. Pause/Reprise : Trouver les abonnements en pause
    List<Abonnement> findByStatut(StatutAbonnement statut);

    // 2. Renouvellement Automatique : Trouver les abonnements à renouveler
    List<Abonnement> findByRenouvellementAutoAndStatut(boolean renouvellementAuto, StatutAbonnement statut);

    // 3. Requête personnalisée pour les abonnements expirant dans 3 jours
    @Query("SELECT a FROM Abonnement a WHERE a.statut = 'ACTIF' AND a.dateFin BETWEEN :today AND :dateLimite")
    List<Abonnement> findAbonnementsARenouveler(
            @Param("today") LocalDate today,
            @Param("dateLimite") LocalDate dateLimite
    );

    // 4. Stats avancées : Nombre d'abonnements par statut (pour dashboard)
    @Query("SELECT a.statut, COUNT(a) FROM Abonnement a GROUP BY a.statut")
    List<Object[]> countAbonnementsByStatut();

    // 5. Revenus mensuels (ex: pour un rapport financier)
    @Query("SELECT SUM(a.prix) FROM Abonnement a WHERE YEAR(a.dateDebut) = :annee AND MONTH(a.dateDebut) = :mois")
    Double calculateRevenueForMonth(@Param("mois") int mois, @Param("annee") int annee);
}