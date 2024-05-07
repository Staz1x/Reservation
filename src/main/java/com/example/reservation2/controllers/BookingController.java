package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.BookingNotFoundException;
import com.example.reservation2.Exceptions.UserNotFoundException;
import com.example.reservation2.models.Booking;
import com.example.reservation2.models.User;
import com.example.reservation2.services.BookingService;
import com.example.reservation2.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/booking/{userId}")
    public List<Booking> getBookingsByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        List<Booking> bookings = bookingService.getBookingsByUser(user);
        if (bookings.isEmpty()) {
            throw new BookingNotFoundException("No bookings found for user: " + userId);
        }
        return bookings;
    }
/*    @GetMapping("/user/{userId}")
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
    }*/

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Here you can handle creation of Booking and BookingDate
        // Let's assume BookingDate needs to be updated separately
        // You may also perform any validation or business logic checks here

        // Save the booking
        Booking createdBooking = bookingService.createBooking(booking);

        // Update BookingDate - Example code (you may need to adjust based on your logic)
        // BookingDate updatedBookingDate = updateBookingDate(booking.getBookingDate());

        // Return the created booking
        return createdBooking;
    }



}
