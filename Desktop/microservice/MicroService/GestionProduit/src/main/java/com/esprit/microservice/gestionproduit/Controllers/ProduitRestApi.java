package com.esprit.microservice.gestionproduit.Controllers;


import com.esprit.microservice.gestionproduit.Entites.Produit;
import com.esprit.microservice.gestionproduit.Services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitRestApi {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/afficher")
    public List<Produit> getAll() {
        return produitService.getAll();
    }

    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return produitService.getById(id).orElse(null);
    }

    @PostMapping("/add")
    public Produit create(@RequestBody Produit produit) {
        return produitService.create(produit);
    }

    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.update(id, produit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }
    @GetMapping("/search")
    public List<Produit> search(
            @RequestParam String categorie,
            @RequestParam double prixMax
    ) {
        return produitService.searchByCategorieAndPrix(categorie, prixMax);
    }
    @PutMapping("/apply-promo")
    public List<Produit> applyPromo() {
        return produitService.appliquerPromotions();
    }

}