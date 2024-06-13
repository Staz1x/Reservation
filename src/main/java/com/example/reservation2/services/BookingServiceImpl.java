package com.example.reservation2.services;

import com.example.reservation2.Exceptions.BookingNotFoundException;
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {


    private BookingRepository bookingRepository;

    private BookingDateRepository bookingDateRepository;

    private RoomService roomService;


    private UserService userService;


    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingDateRepository bookingDateRepository, RoomService roomService,
                              UserService userService) {
        this.bookingRepository = bookingRepository;
        this.bookingDateRepository = bookingDateRepository;
        this.roomService = roomService;
        this.userService = userService;


    }


    @Override
    public Booking createBooking(Long userId, Long roomId, LocalDate startDate, LocalDate endDate) {
        User user = userService.getUserById(userId);

        Room room = roomService.getRoomById(roomId);

        // Check Room availability
        if (!isRoomAvailable(roomId, startDate, endDate)) {
            throw new RoomUnavailableException("Room is not available for the specified dates");
        }

        // Create a booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        // Save booking, BookingDates need a bookingId

        booking = bookingRepository.save(booking);

        // Create bookingDates for every day in the booking
        Set<BookingDate> bookingDates = new HashSet<>();
        for (LocalDate date = startDate; !date.isAfter(endDate)|| date.isEqual(endDate)  ; date = date.plusDays(1)) {
            BookingDate bookingDate = new BookingDate();
            bookingDate.setDate(date);
            bookingDate.setBooking(booking);
            bookingDate.setRoom(room);
            bookingDates.add(bookingDate);
        }
        //Save bookingDates to database


        bookingDateRepository.saveAll(bookingDates);

        // Return the booking.

        return booking;
    }

    @Override
    @Transactional
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found"));
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        // check all bookings for a specific room to see if It's available or not.
        List<Booking> bookings = findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(roomId, startDate, endDate);

        // If there are no bookings, return true.
        return bookings.isEmpty();
    }

    @Override
    @Transactional
    public void deleteBookingById(Long id) {
        bookingDateRepository.deleteBookingDateByBooking_BookingId(id);
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> findBookingsByRoomIdAndStartDateBetweenOrEndDateBetween(Long roomId, LocalDate startDate, LocalDate endDate) {

        List<Booking> bookings = new ArrayList<>();
        //Find bookings with specific room withing specific date-range and add them to a list
        for (Booking booking : getAllBookings()) {
            if (booking.getRoom().getRoomId() == roomId &&
                    ((booking.getStartDate().isAfter(startDate) || booking.getStartDate().isEqual(startDate)) &&
                            (booking.getStartDate().isBefore(endDate) || booking.getStartDate().isEqual(endDate)))) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    @Override
    @Transactional
    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<Room> allRoomsAvailable = roomService.getAllRooms();
        List<BookingDate> allBookingDates = bookingDateRepository.findAll();


        List<Long> bookedRoomsId = allBookingDates.stream()
                .filter(bookingDate ->
                        !bookingDate.getDate().isBefore(startDate) && bookingDate.getDate().isBefore(endDate))
                .map(bookingDate -> bookingDate.getBooking().getRoom().getRoomId())
                .distinct()
                .collect(Collectors.toList());


        List<Room> availableRooms = allRoomsAvailable.stream()
                .filter(room -> !bookedRoomsId.contains(room.getRoomId()))
                .collect(Collectors.toList());

        return availableRooms;
    }

    /*public boolean deleteBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if (booking.isPresent()) {
            LocalDate now = LocalDate.now();
            LocalDate bookingStartDate = booking.get().getStartDate();
            long daysBetween = ChronoUnit.DAYS.between(now, bookingStartDate);
            if (daysBetween > 1) { // Ensure there is more than one day difference
                bookingRepository.deleteById(bookingId);
                return true;
            }
        }
        return false;
    }*/

}
