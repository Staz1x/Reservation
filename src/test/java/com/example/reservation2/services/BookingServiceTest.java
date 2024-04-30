package com.example.reservation2.services;

import com.example.reservation2.enums.UserRole;
import com.example.reservation2.models.Booking;
import com.example.reservation2.models.BookingDate;
import com.example.reservation2.models.Role;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.BookingRepository;
import com.example.reservation2.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookingServiceTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    Booking mockBooking;



    @BeforeEach
    public void setup(){
        User testUser = new User();
        Role role = new Role();
        mockBooking = new Booking();

        role.setId(1L);
        role.setRoleName(UserRole.USER);

        testUser.setUserId(1);
        testUser.setEmail("test@testmail.se");
        testUser.setFirstName("Teston");
        testUser.setLastName("Testsson");
        testUser.setRole(role);
        testUser.setPassword("123456");
        testUser.setPhoneNr("0703000000");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Skapa java.util.Date för startdatumet
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        Date startDateUtil = new Date(startCalendar.getTimeInMillis());

        // Konvertera java.util.Date till java.sql.Date
        Date startDateSql = new Date(startDateUtil.getTime());

        // Skapa java.util.Date för slutdatumet
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2024, Calendar.APRIL, 27); // År, månad, dag
        Date endDateUtil = new Date(endCalendar.getTimeInMillis());

        // Konvertera java.util.Date till java.sql.Date
        Date endDateSql = new Date(endDateUtil.getTime());

        // Ställ in bokningen med användare och datum
        mockBooking.setUser(testUser);
        mockBooking.setBookingId(2L);
        mockBooking.setBookingDate(timestamp);
        mockBooking.setStartDate(startDateSql);
        mockBooking.setEndDate(endDateSql);

        // Skapa en bokningsdatum och lägg till i mockbokningen
        BookingDate bookingDate = new BookingDate();

        // Skapa ett datum för bokningsdatumet
        Calendar bookingDateCalendar = Calendar.getInstance();
        bookingDateCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        Date date = new Date(bookingDateCalendar.getTimeInMillis());
        bookingDate.setDate(date);

        // Tilldela mockbokningen till bokningsdatumet
        bookingDate.setBooking(mockBooking);

        // Lägg till bokningsdatumet i mockbokningen
        mockBooking.getBookingDates().add(bookingDate);

    }




    @Test
    void testCreateBooking() {
        when(bookingRepository.save(any(Booking.class))).thenReturn(mockBooking);

        Booking createdBooking = bookingService.createBooking(mockBooking);

        verify(bookingRepository, times(1)).save(mockBooking);

        assertEquals(mockBooking, createdBooking, "Returned booking should be equal to mockBooking");
    }

    @Test
    void testGetAllBookings() {

        List<Booking> mockBookings = new ArrayList<>();

        mockBookings.add(mockBooking);

        when(bookingRepository.findAll()).thenReturn(mockBookings);

        List<Booking> actualBookings = bookingService.getAllBookings();

        assertEquals(mockBookings, actualBookings);
        assertEquals(mockBookings.size(), actualBookings.size());
        verify(bookingRepository, times(1)).findAll();

    }

    @Test
    void getBookingById() {

        Long bookingId = 5L;

        Long falseBookingId = 1L;

        when(bookingRepository.findById(bookingId)).thenReturn(java.util.Optional.ofNullable(mockBooking));

        Booking actualBooking = bookingService.getBookingById(bookingId);
        Booking falseBooking = bookingService.getBookingById(falseBookingId);


        assertEquals(mockBooking, actualBooking);
        assertNotEquals(mockBooking, falseBooking);

        verify(bookingRepository, times(1)).findById(bookingId);


    }

    @Test
    void getBookingsByUser() {
    }

    @Test
    void deleteBookingById() {
    }
}