package com.esprit.microservice.gestionabonnement.Controllers;

import com.esprit.microservice.gestionabonnement.Entites.Abonnement;
import com.esprit.microservice.gestionabonnement.Entites.StatutAbonnement;
import com.esprit.microservice.gestionabonnement.Entites.TypeAbonnement;
import com.esprit.microservice.gestionabonnement.Services.AbonnementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abonnements")
@RequiredArgsConstructor
public class AbonnementRestAPI {

    private final AbonnementService abonnementService;

    //  Créer un abonnement
    @PostMapping
    public ResponseEntity<Abonnement> creerAbonnement(@RequestBody Abonnement abonnement) {
        Abonnement newAbonnement = abonnementService.creerAbonnement(abonnement);
        return ResponseEntity.ok(newAbonnement);
    }

    //  Récupérer un abonnement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Abonnement> getAbonnementById(@PathVariable Long id) {
        Optional<Abonnement> abonnement = abonnementService.getAbonnementById(id);
        return abonnement.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  Récupérer tous les abonnements d’un utilisateur (Keycloak userId)
    @GetMapping("/utilisateur/{userId}")
    public ResponseEntity<List<Abonnement>> getAbonnementsByUserId(@PathVariable String userId) {
        List<Abonnement> abonnements = abonnementService.getAbonnementsByUserId(userId);
        return ResponseEntity.ok(abonnements);
    }

    // Annuler un abonnement
    @PutMapping("/{id}/annuler")
    public ResponseEntity<Abonnement> annulerAbonnement(@PathVariable Long id) {
        Abonnement abonnement = abonnementService.annulerAbonnement(id);
        return abonnement != null ? ResponseEntity.ok(abonnement) : ResponseEntity.notFound().build();
    }

    //  Vérifier si un utilisateur a un abonnement actif
    @GetMapping("/utilisateur/{userId}/actif")
    public ResponseEntity<Boolean> utilisateurAAbonnementActif(@PathVariable String userId) {
        boolean actif = abonnementService.utilisateurAAbonnementActif(userId);
        return ResponseEntity.ok(actif);
    }
    @GetMapping("/debug/all")
    public ResponseEntity<List<Abonnement>> getAllAbonnements() {
        return ResponseEntity.ok(abonnementService.getAll());
    }
    @GetMapping("/statistiques/actifs")
    public ResponseEntity<Long> countAbonnementsActifs() {
        long count = abonnementService.countAbonnementsByStatut(StatutAbonnement.ACTIF);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Abonnement>> getAbonnementsByType(@PathVariable TypeAbonnement type) {
        List<Abonnement> abonnements = abonnementService.getAbonnementsByType(type);
        return ResponseEntity.ok(abonnements);
    }
    @DeleteMapping("/delete/{id}")
    public void removeAbon(@PathVariable("id") Long chId) {
        abonnementService.deleteAbon(chId);
    }}






