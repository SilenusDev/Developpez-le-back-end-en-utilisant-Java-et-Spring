package com.firstapi.controller;

import com.firstapi.models.Rental;
import com.firstapi.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalController {
    private final RentalService rentalService;
    
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    
    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    
    @GetMapping("/rentals/{id}")
    public ResponseEntity<Rental> getRental(@PathVariable Long id) {
        return rentalService.getRentalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
