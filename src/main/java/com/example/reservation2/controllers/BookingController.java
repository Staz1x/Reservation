package com.example.reservation2.controllers;

import com.example.reservation2.Exceptions.BookingNotFoundException;
import com.example.reservation2.Exceptions.RoomNotFoundException;
import com.example.reservation2.Exceptions.RoomUnavailableException;
import com.example.reservation2.Exceptions.UserNotFoundException;
import com.example.reservation2.models.Booking;
import com.example.reservation2.models.Room;
import com.example.reservation2.models.User;
import com.example.reservation2.services.BookingDateService;
import com.example.reservation2.services.BookingService;
import com.example.reservation2.services.RoomService;
import com.example.reservation2.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    private BookingDateService bookingDateService;

    private final UserService userService;

    private final RoomService roomService;



    public BookingController(BookingService bookingService, UserService userService,
                             BookingDateService bookingDateService, RoomService roomService){
        this.bookingService = bookingService;
        this.userService = userService;
        this.bookingDateService = bookingDateService;
        this.roomService = roomService;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/booking/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
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

@PostMapping("/create")
public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
    // Kontrollera om användaren finns
    System.out.println("Booking started");
    User user = userService.getUserById(booking.getUser().getUserId());
    if (user == null) {
        System.out.println("Hittar inte ID");
        throw new UserNotFoundException("User not found with id: " + booking.getUser().getUserId());
    }
    System.out.println("Hittar ID");

    // Kontrollera om rummet finns
    Room room = roomService.getRoomById(booking.getRoom().getRoomId());
    if (room == null) {
        throw new RoomNotFoundException("Room not found with id: " + booking.getRoom().getRoomId());
    }

    // Kontrollera om rummet är tillgängligt för de angivna datumen
    if (!bookingService.isRoomAvailable(room.getRoomId(), booking.getStartDate(), booking.getEndDate())) {
        throw new RoomUnavailableException("Room is not available for the specified dates");
    }

    // Skapa bokningen och spara den i databasen
    Booking createdBooking = bookingService.createBooking(booking.getUser().getUserId(), room.getRoomId(), booking.getStartDate(), booking.getEndDate());

    // Returnera den skapade bokningen som en ResponseEntity med HTTP-status 201 Created
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
}

    /*@DeleteMapping("/")
    void deleteBookingById(@RequestParam Long id){
        bookingService.deleteBookingById(id);
    }*/

    @DeleteMapping("/")
    public ResponseEntity<?> deleteBookingById(@RequestParam Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok().build();
    }

}
