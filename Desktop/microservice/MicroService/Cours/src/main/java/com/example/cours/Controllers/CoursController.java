package com.example.cours.Controllers;

import com.example.cours.Entites.Cours;
import com.example.cours.Services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CoursController {
    @Autowired
    private CoursService coursService;

    @GetMapping("/")
    public String home() {
        return "Bienvenue dans le microservice Cours 📚";
    }

    // Récupérer tous les cours
    @GetMapping("/retrieve-all-Courses")
    public List<Cours> getAllCourses() {
        List<Cours> listCours = coursService.getAllCourses();
        return listCours;
    }

    // Récupérer un cours par ID
    @GetMapping("show/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable Long id) {
        Optional<Cours> cours = coursService.getCoursById (id);
        return cours.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/add-cours")
    public Cours addCours(@RequestBody Cours cours) {
        System.out.println("📦 Reçu depuis Postman : " + cours);
        return coursService.addCours(cours);
    }

    @Override
    public String toString() {
        return "CoursController{" +
                "coursService=" + coursService +
                '}';
    }

    // Modifier un cours
    @PutMapping("/{id}")
    public Cours updateCours(@RequestBody Cours c) {
        Cours cours = coursService.updateCours(c);
        return cours;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Cours> findCoursById(@PathVariable Long id) {
        Optional<Cours> cours = coursService.getCoursById(id);
        return cours.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public void removeCours(@PathVariable("id") Long chId) {
        coursService.deleteCours(chId);
    }}
