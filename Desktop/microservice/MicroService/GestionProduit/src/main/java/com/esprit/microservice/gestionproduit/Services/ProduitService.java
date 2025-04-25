package com.esprit.microservice.gestionproduit.Services;
import com.esprit.microservice.gestionproduit.Entites.Produit;
import com.esprit.microservice.gestionproduit.Repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    public Optional<Produit> getById(Long id) {
        return produitRepository.findById(id);
    }

    public Produit create(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit update(Long id, Produit produitData) {
        return produitRepository.findById(id).map(p -> {
            p.setNom(produitData.getNom());
            p.setCategorie(produitData.getCategorie());
            p.setPrix(produitData.getPrix());
            p.setStock(produitData.getStock());
            return produitRepository.save(p);
        }).orElse(null);
    }

    public void delete(Long id) {
        produitRepository.deleteById(id);
    }
    public List<Produit> searchByCategorieAndPrix(String categorie, double prixMax) {
        return produitRepository.findByCategorieAndPrixLessThanEqual(categorie, prixMax);
    }
    public List<Produit> appliquerPromotions() {
        List<Produit> produits = produitRepository.findAll();

        for (Produit p : produits) {
            if (p.getStock() > 100) {
                p.setRemise(10.0); // Applique 10% de remise
                double nouveauPrix = p.getPrix() * 0.9; // 10% de réduction
                p.setPrix(nouveauPrix);
            } else {
                p.setRemise(0.0); // Pas de remise
            }
        }
        return produitRepository.saveAll(produits);
    }

}