package com.firstapi.service;

import com.firstapi.models.Rental;
import com.firstapi.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    
    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }
    
    // Récupérer toutes les locations
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    
    // Récupérer une location par son ID
    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }
    
    // Récupérer les locations d'un propriétaire
    public List<Rental> getRentalsByOwnerId(Long ownerId) {
        return rentalRepository.findByOwnerId(ownerId);
    }
    
    // Rechercher des locations par nom
    public List<Rental> searchRentalsByName(String name) {
        return rentalRepository.findByNameContaining(name);
    }
    
    // Compter les locations d'un propriétaire
    public long countRentalsByOwner(Long ownerId) {
        return rentalRepository.countByOwnerId(ownerId);
    }
    
    // Créer une nouvelle location
    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }
    
    // Mettre à jour une location
    public Optional<Rental> updateRental(Long id, Rental rentalDetails) {
        return rentalRepository.findById(id)
            .map(existingRental -> {
                // Update only non-null fields
                if (rentalDetails.getName() != null) {
                    existingRental.setName(rentalDetails.getName());
                }
                if (rentalDetails.getSurface() != null) {
                    existingRental.setSurface(rentalDetails.getSurface());
                }
                if (rentalDetails.getPrice() != null) {
                    existingRental.setPrice(rentalDetails.getPrice());
                }
                if (rentalDetails.getPicture() != null) {
                    existingRental.setPicture(rentalDetails.getPicture());
                }
                if (rentalDetails.getDescription() != null) {
                    existingRental.setDescription(rentalDetails.getDescription());
                }
                return rentalRepository.save(existingRental);
            });
    }
    
    // Supprimer une location
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
}
