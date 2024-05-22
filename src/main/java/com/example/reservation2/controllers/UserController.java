package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.UserNotFoundException;
import com.example.reservation2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.reservation2.models.User;
import com.example.reservation2.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/")
    public List<User> getAllUsers(){
      return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(Map.of("message", "Login successful", "userId", user.getUserId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getUserNameById(@PathVariable Long id){
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user.getFirstName() + " " + user.getLastName());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
