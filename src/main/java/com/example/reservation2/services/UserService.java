package com.example.reservation2.services;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;

import java.util.List;

public interface UserService {


    User getUserById(Long id);


    User findUserByEmail(String email);

    User createUser(User user);
}
