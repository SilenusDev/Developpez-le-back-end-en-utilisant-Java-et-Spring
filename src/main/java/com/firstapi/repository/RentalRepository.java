package com.firstapi.repository;

import com.firstapi.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> findByOwnerId(Integer ownerId); // Trouver toutes les locations d'un propriétaire

    List<Rental> findByNameContaining(String name); // Recherche de locations par nom

    long countByOwnerId(Integer ownerId); // Compte des locations par propriétaire
}


