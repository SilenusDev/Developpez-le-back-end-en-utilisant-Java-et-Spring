package com.firstapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.firstapi.models.User;
import com.firstapi.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
