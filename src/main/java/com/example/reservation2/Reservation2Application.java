package com.example.reservation2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Reservation2Application {

    public static void main(String[] args) {
        SpringApplication.run(Reservation2Application.class, args);
    }

}
