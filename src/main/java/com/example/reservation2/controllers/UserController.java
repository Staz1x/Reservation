package com.example.reservation2.controllers;

import com.example.reservation2.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
}
