package com.example.cours.Services;

import com.example.cours.Entites.Cours;
import com.example.cours.Repositories.CoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAllCourses() {
        return coursRepository.findAll();
    }

    public Optional<Cours> getCoursById(Long id) {
        return coursRepository.findById(id);
    }

    public Cours addCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public Cours updateCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }
}
