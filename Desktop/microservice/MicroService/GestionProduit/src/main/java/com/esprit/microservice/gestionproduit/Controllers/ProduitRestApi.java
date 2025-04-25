package com.esprit.microservice.gestionproduit.Controllers;

import com.esprit.microservice.gestionproduit.Entites.Produit;
import com.esprit.microservice.gestionproduit.Services.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produits")
@RequiredArgsConstructor
public class ProduitRestApi {

    @Autowired
    private ProduitService produitService;

    // Accessible par les utilisateurs avec le rôle "user"
    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/afficher")
    public List<Produit> getAll() {
        return produitService.getAll();
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/{id}")
    public Produit getById(@PathVariable Long id) {
        return produitService.getById(id).orElse(null);
    }

    // Accessible uniquement par un admin
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/add")
    public Produit create(@RequestBody Produit produit) {
        return produitService.create(produit);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/{id}")
    public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.update(id, produit);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }

    @PreAuthorize("hasRole('user') or hasRole('admin')")
    @GetMapping("/search")
    public List<Produit> search(@RequestParam String categorie, @RequestParam double prixMax) {
        return produitService.searchByCategorieAndPrix(categorie, prixMax);
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/apply-promo")
    public List<Produit> applyPromo() {
        return produitService.appliquerPromotions();
    }
}
