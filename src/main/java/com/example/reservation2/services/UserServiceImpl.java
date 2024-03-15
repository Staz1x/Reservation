package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.RoleRepository;
import com.example.reservation2.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
