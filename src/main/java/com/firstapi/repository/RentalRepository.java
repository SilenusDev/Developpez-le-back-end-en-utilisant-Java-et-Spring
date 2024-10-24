// RentalRepository.java
package com.firstapi.repository;

import com.firstapi.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {  // Changed Integer to Long for consistency
    List<Rental> findByOwnerId(Long ownerId);
    List<Rental> findByNameContaining(String name);
    long countByOwnerId(Long ownerId);
}


