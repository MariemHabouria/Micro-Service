package com.esprit.microservice.gestionproduit.Repositories;

import com.esprit.microservice.gestionproduit.Entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategorieAndPrixLessThanEqual(String categorie, double prix);

}