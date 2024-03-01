package com.example.reservation2.repositories;

import com.example.reservation2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<User, Long> {

}
