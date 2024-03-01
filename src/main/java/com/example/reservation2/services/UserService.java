package com.example.reservation2.services;

import com.example.reservation2.models.User;

public interface UserService {

    User findUserByEmail(String email);
}
