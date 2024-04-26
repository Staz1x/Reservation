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
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        Date startDate = (Date) startCalendar.getTime();

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2024, Calendar.APRIL, 27); // År, månad, dag
        Date endDate = (Date) endCalendar.getTime();

        mockBooking.setUser(testUser);
        mockBooking.setBookingId(2);
        mockBooking.setBookingDate(timestamp);
        mockBooking.setStartDate(startDate);
        mockBooking.setEndDate(endDate);

        BookingDate bookingDate = new BookingDate();

        Calendar bookingDateCalendar = Calendar.getInstance();
        bookingDateCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        Date date = new Date(bookingDateCalendar.getTimeInMillis());
        bookingDate.setDate(date);

        bookingDate.setBooking(mockBooking);

        mockBooking.getBookingDates().add(bookingDate);

    }




    @Test
    void testCreateBooking() {
    }

    @Test
    void getAllBookings() {
    }

    @Test
    void getBookingById() {
    }

    @Test
    void getBookingsByUser() {
    }

    @Test
    void deleteBookingById() {
    }
}