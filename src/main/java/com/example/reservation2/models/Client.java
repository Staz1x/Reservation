package com.example.reservation2.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String phoneNr;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;



}
