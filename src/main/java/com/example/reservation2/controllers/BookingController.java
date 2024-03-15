package com.example.reservation2.controllers;

import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;
import com.example.reservation2.services.BookingService;
import com.example.reservation2.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    private UserService userService;



    public BookingController(BookingService bookingService, UserService userService){
        this.bookingService = bookingService;
        this.userService = userService;
    }

/*
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            //Create Exception if needed.
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return bookingService.getBookingsByUser(user);
    }
*/
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            // User not found, return HTTP 404 Not Found
            return ResponseEntity.notFound().build();
        }
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        if (bookings.isEmpty()) {
            // No bookings found, return HTTP 204 No Content
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }



}
