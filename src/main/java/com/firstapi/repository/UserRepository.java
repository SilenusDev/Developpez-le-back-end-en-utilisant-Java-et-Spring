package com.firstapi.repository;

import com.firstapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    
    List<User> findByNameContaining(String name); // Recherche d'utilisateurs par nom
    
    void deleteByEmail(String email); // Suppression d'un utilisateur par e-mail
}


