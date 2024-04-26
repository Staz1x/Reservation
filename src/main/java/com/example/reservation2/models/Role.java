package com.example.reservation2.models;

import com.example.reservation2.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UserRole roleName;

}
