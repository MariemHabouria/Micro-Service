package com.esprit.microservice.gestionabonnement.Services;

import com.esprit.microservice.gestionabonnement.Entites.Abonnement;
import com.esprit.microservice.gestionabonnement.Entites.StatutAbonnement;
import com.esprit.microservice.gestionabonnement.Repositories.AbonnementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AbonnementService {
    @Autowired
    private final AbonnementRepository abonnementRepository;

    // Créer un abonnement
    public Abonnement creerAbonnement(Abonnement abonnement) {
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setDateFin(calculerDateFin(abonnement));
        abonnement.setStatut(StatutAbonnement.ACTIF);
        return abonnementRepository.save(abonnement);
    }

    // Trouver un abonnement par ID
    public Optional<Abonnement> getAbonnementById(Long id) {
        return abonnementRepository.findById(id);
    }

    // Trouver tous les abonnements d'un utilisateur
    public List<Abonnement> getAbonnementsByUserId(String userId) {
        return abonnementRepository.findByUserId(userId);
    }

    // Annuler un abonnement
    public Abonnement annulerAbonnement(Long id) {
        Optional<Abonnement> abonnementOpt = abonnementRepository.findById(id);
        if (abonnementOpt.isPresent()) {
            Abonnement abonnement = abonnementOpt.get();
            abonnement.setStatut(StatutAbonnement.ANNULE);
            return abonnementRepository.save(abonnement);
        }
        return null;
    }

    // Vérifier si un utilisateur a un abonnement actif
    public boolean utilisateurAAbonnementActif(String userId) {
        return abonnementRepository.findByUserIdAndStatut(userId, StatutAbonnement.ACTIF).isPresent();
    }

    // Calculer la date de fin en fonction du type d'abonnement
    private LocalDate calculerDateFin(Abonnement abonnement) {
        switch (abonnement.getType()) {
            case MENSUEL:
                return LocalDate.now().plusMonths(1);
            case TRIMESTRIEL:
                return LocalDate.now().plusMonths(3);
            case ANNUEL:
                return LocalDate.now().plusYears(1);
            default:
                return LocalDate.now();
        }
    }
    public Abonnement mettreEnPause(Long id) {
        Optional<Abonnement> abonnementOpt = abonnementRepository.findById(id);
        if (abonnementOpt.isPresent()) {
            Abonnement abonnement = abonnementOpt.get();
            abonnement.setStatut(StatutAbonnement.EN_PAUSE);
            return abonnementRepository.save(abonnement);
        }
        return null;
    }

    // Reprendre un abonnement après une pause
    public Abonnement reprendreAbonnement(Long id) {
        Optional<Abonnement> abonnementOpt = abonnementRepository.findById(id);
        if (abonnementOpt.isPresent()) {
            Abonnement abonnement = abonnementOpt.get();
            abonnement.setStatut(StatutAbonnement.ACTIF);
            // Ajuster la date de fin en fonction des jours restants avant la pause
            long joursRestants = ChronoUnit.DAYS.between(LocalDate.now(), abonnement.getDateFin());
            abonnement.setDateFin(LocalDate.now().plusDays(joursRestants));
            return abonnementRepository.save(abonnement);
        }
        return null;
    }
    // Activer/désactiver le renouvellement automatique
    public Abonnement toggleRenouvellementAuto(Long id, boolean active) {
        Optional<Abonnement> abonnementOpt = abonnementRepository.findById(id);
        if (abonnementOpt.isPresent()) {
            Abonnement abonnement = abonnementOpt.get();
            abonnement.setRenouvellementAuto(active);
            return abonnementRepository.save(abonnement);
        }
        return null;
    }

    // Tâche planifiée pour renouveler les abonnements (à exécuter quotidiennement)
    @Scheduled(cron = "0 0 0 * * ?") // Minuit chaque jour
    public void renouvelerAbonnements() {
        List<Abonnement> abonnements = abonnementRepository.findByRenouvellementAutoAndStatut(true, StatutAbonnement.ACTIF);
        abonnements.stream()
                .filter(a -> LocalDate.now().isAfter(a.getDateFin().minusDays(3))) // 3 jours avant expiration
                .forEach(a -> {
                    Abonnement nouvelAbonnement = new Abonnement();
                    nouvelAbonnement.setUserId(a.getUserId());
                    nouvelAbonnement.setType(a.getType());
                    nouvelAbonnement.setPrix(a.getPrix());
                    creerAbonnement(nouvelAbonnement); // Crée un nouvel abonnement
                });
    }

    // Stats
    public Map<StatutAbonnement, Long> getStatsAbonnements() {
        List<Object[]> stats = abonnementRepository.countAbonnementsByStatut();
        return stats.stream()
                .collect(Collectors.toMap(
                        e -> StatutAbonnement.valueOf((String) e[0]),
                        e -> (Long) e[1]
                ));
    }

    // Revenus
    public Double getRevenusMensuels(int mois, int annee) {
        return abonnementRepository.calculateRevenueForMonth(mois, annee);
    }
}
