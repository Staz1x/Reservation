package com.example.reservation2.services;

import com.example.reservation2.models.User;
import com.example.reservation2.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}