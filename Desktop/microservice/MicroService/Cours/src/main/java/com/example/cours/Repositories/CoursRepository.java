package com.example.cours.Repositories;

import com.example.cours.Entites.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours,Long> {
    List<Cours> findAll();

}
