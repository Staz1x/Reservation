package com.example.reservation2.services;

import com.example.reservation2.Exceptions.RoomNotFoundException;
import com.example.reservation2.Exceptions.RoomUnavailableException;
import com.example.reservation2.Exceptions.UserNotFoundException;
import com.example.reservation2.models.Booking;
import com.example.reservation2.models.BookingDate;
import com.example.reservation2.models.Room;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.BookingDateRepository;
import com.example.reservation2.repositories.BookingRepository;
import com.example.reservation2.repositories.RoomRepository;
import com.example.reservation2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService{






    private BookingRepository bookingRepository;

    private  BookingDateRepository bookingDateRepository;

    private RoomRepository roomRepository;


    private UserRepository userRepository;



    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingDateRepository bookingDateRepository, RoomRepository roomRepository,
                              UserRepository userRepository){
        this.bookingRepository = bookingRepository;
        this.bookingDateRepository = bookingDateRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;


    }



    @Override
    public Booking createBooking(Long userId, Long roomId, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        // Kontrollera om rummet är tillgängligt för de angivna datumen
        if (!isRoomAvailable(roomId, startDate, endDate)) {
            throw new RoomUnavailableException("Room is not available for the specified dates");
        }

        // Skapa en ny bokning
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        // Skapa bookingDates för varje dag mellan start- och slutdatum
        Set<BookingDate> bookingDates = new HashSet<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            BookingDate bookingDate = new BookingDate();
            bookingDate.setDate(date);
            bookingDate.setBooking(booking);//Need to fix a bookingID, now its
            bookingDate.setRoom(room);
            bookingDates.add(bookingDate);
        }


        booking.setBookingDates(bookingDates);

        // Spara bokningen och dess bookingDates i databasen

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        // Hämta alla bokningar för rummet mellan de angivna datumen
        List<Booking> bookings = findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(roomId, startDate, endDate, startDate, endDate);

        // Om det inte finns några bokningar, är rummet tillgängligt
        return bookings.isEmpty();
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate, LocalDate startDate2, LocalDate endDate2) {
        // Implementera logiken för att hämta bokningar för ett rum och mellan två perioder här
        List<Booking> bookings = new ArrayList<>();
        // Exempellogik: här kan du filtrera och hämta de bokningar som matchar dina kriterier
        for (Booking booking : getAllBookings()) {
            if (booking.getRoom().getRoomId() == roomId &&
                    ((booking.getStartDate().isAfter(startDate) || booking.getStartDate().isEqual(startDate)) &&
                            (booking.getStartDate().isBefore(endDate) || booking.getStartDate().isEqual(endDate))) ||
                    ((booking.getEndDate().isAfter(startDate2) || booking.getEndDate().isEqual(startDate2)) &&
                            (booking.getEndDate().isBefore(endDate2) || booking.getEndDate().isEqual(endDate2)))) {
                bookings.add(booking);
            }
        }
        return bookings;
    }
}
