package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.BookingNotFoundException;
import com.example.reservation2.Exceptions.UserNotFoundException;
import com.example.reservation2.models.Booking;
import com.example.reservation2.models.BookingDate;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.BookingRepository;
import com.example.reservation2.services.BookingDateService;
import com.example.reservation2.services.BookingService;
import com.example.reservation2.services.UserService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private BookingService bookingService;

    private BookingDateService bookingDateService;

    private UserService userService;



    public BookingController(BookingService bookingService, UserService userService,
                             BookingDateService bookingDateService){
        this.bookingService = bookingService;
        this.userService = userService;
        this.bookingDateService = bookingDateService;
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

//    @PostMapping("/")
//    public ResponseEntity<?> createBooking(@Valid @RequestBody Booking booking, BindingResult bindingResult) {
//        // Kontrollera om valideringsfel har inträffat
//        if (bindingResult.hasErrors()) {
//            // Om valideringsfel finns, returnera felmeddelanden
//            return ResponseEntity.badRequest().body("Några obligatoriska attribut saknas eller är ogiltiga");
//        }
//
//        // Annars fortsätt med att skapa bokningen
//        Booking createdBooking = bookingService.createBooking(booking);
//
//        // Returnera den skapade bokningen som en ResponseEntity
//        return ResponseEntity.ok(createdBooking);
//    }
@PostMapping("/")
public Booking createBooking(@RequestBody Booking booking) {
    // Spara bokningen först
    Booking createdBooking = bookingService.createBooking(booking);

    // Loopa igenom och spara varje bokningsdatum med referens till den sparade bokningen
    for (BookingDate bookingDate : createdBooking.getBookingDates()) {
        bookingDate.setBooking(createdBooking);
        bookingDateService.saveBookingDate(bookingDate);
    }

    return createdBooking;
}

    @DeleteMapping("/")
    void deleteBookingById(Long id){
        bookingService.deleteBookingById(id);
    }



}
