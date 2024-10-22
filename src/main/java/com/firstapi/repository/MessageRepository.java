package com.firstapi.repository;

import com.firstapi.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRentalId(Integer rentalId); // Trouver tous les messages pour une location

    List<Message> findByUserId(Integer userId); // Trouver tous les messages d'un utilisateur
}
