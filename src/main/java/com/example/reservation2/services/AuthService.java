package com.example.reservation2.services;

import com.example.reservation2.models.User;
import com.example.reservation2.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String email, String password) {
        Optional<User> findUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (findUser.isPresent()) {
            User user = findUser.get();
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }

}
