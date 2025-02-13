package com.esprit.microservice.gestionabonnement.Services;

import com.esprit.microservice.gestionabonnement.Entites.Abonnement;
import com.esprit.microservice.gestionabonnement.Entites.StatutAbonnement;
import com.esprit.microservice.gestionabonnement.Repositories.AbonnementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
}
