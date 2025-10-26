package com.example.filiereservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.filiereservice.entitie.Filiere;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
}