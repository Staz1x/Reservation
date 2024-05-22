package com.example.reservation2.services;

import com.example.reservation2.enums.UserRole;
import com.example.reservation2.models.*;
import com.example.reservation2.repositories.BookingDateRepository;
import com.example.reservation2.repositories.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingDateRepository bookingDateRepository;

    @Mock
    private UserService userService;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private BookingServiceImpl bookingService;
    @Mock
    Booking mockBooking;

    @Mock
    User mockUser;



    @BeforeEach
    public void setup(){
        mockUser = new User();
        Role role = new Role();


        role.setRoleId(1L);
        role.setRoleName(UserRole.USER);

        mockUser.setUserId(1);
        mockUser.setEmail("test@testmail.se");
        mockUser.setFirstName("Teston");
        mockUser.setLastName("Testsson");
        mockUser.setRole(role);
        mockUser.setPassword("123456");
        mockUser.setPhoneNr("0703000000");

        mockBooking = new Booking();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Skapa java.util.Date för startdatumet
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        LocalDate startDate = new Date(startCalendar.getTimeInMillis()).toLocalDate();
        Date startDateUtil = new Date(startCalendar.getTimeInMillis());

        // Konvertera java.util.Date till java.sql.Date
        Date startDateSql = new Date(startDateUtil.getTime());

        // Skapa java.util.Date för slutdatumet
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(2024, Calendar.APRIL, 27); // År, månad, dag
        LocalDate endDate =  new Date(endCalendar.getTimeInMillis()).toLocalDate();
        Date endDateUtil = new Date(endCalendar.getTimeInMillis());

        // Konvertera java.util.Date till java.sql.Date
        Date endDateSql = new Date(endDateUtil.getTime());

        // Ställ in bokningen med användare och datum
        mockBooking.setUser(mockUser);
        mockBooking.setBookingId(2L);
        mockBooking.setBookingDate(timestamp);
        mockBooking.setStartDate(startDate);
        mockBooking.setEndDate(endDate);

        // Skapa en bokningsdatum och lägg till i mockbokningen
        BookingDate bookingDate = new BookingDate();

        // Skapa ett datum för bokningsdatumet
        Calendar bookingDateCalendar = Calendar.getInstance();
        bookingDateCalendar.set(2024, Calendar.APRIL, 26); // År, månad, dag
        LocalDate date = new Date(bookingDateCalendar.getTimeInMillis()).toLocalDate();
        bookingDate.setDate(date);

        // Tilldela mockbokningen till bokningsdatumet
        bookingDate.setBooking(mockBooking);

        // Lägg till bokningsdatumet i mockbokningen
        mockBooking.getBookingDates().add(bookingDate);

        MockitoAnnotations.openMocks(this);

    }
    @Test
    public void testFindAvailableRooms() {
        // Setup dummy data
        LocalDate startDate = LocalDate.of(2023, 5, 20);
        LocalDate endDate = LocalDate.of(2023, 5, 25);

        Room room1 = new Room();
        room1.setRoomId(1L);
        room1.setRoomNumber("101");

        Room room2 = new Room();
        room2.setRoomId(2L);
        room2.setRoomNumber("102");

        List<Room> allRooms = new ArrayList<>();
        allRooms.add(room1);
        allRooms.add(room2);

        BookingDate bookingDate1 = new BookingDate();
        bookingDate1.setDate(LocalDate.of(2023, 5, 21));
        Booking booking1 = new Booking();
        booking1.setRoom(room1);
        bookingDate1.setBooking(booking1);

        List<BookingDate> allBookingDates = new ArrayList<>();
        allBookingDates.add(bookingDate1);

        // Mocking
        when(roomService.getAllRooms()).thenReturn(allRooms);
        when(bookingDateRepository.findAll()).thenReturn(allBookingDates);

        // Execute the method under test
        List<Room> availableRooms = bookingService.findAvailableRooms(startDate, endDate);

        // Assert the results
        assertNotNull(availableRooms);
        assertEquals(1, availableRooms.size());
        assertEquals(room2.getRoomId(), availableRooms.get(0).getRoomId());
    }
    @Test
    public void testCreateBooking() {
        // Setup dummy data
        Long userId = 1L;
        Long roomId = 1L;
        LocalDate startDate = LocalDate.of(2024, 5, 20);
        LocalDate endDate = LocalDate.of(2024, 5, 25);

        User user = new User();
        user.setUserId(userId);

        Room room = new Room();
        room.setRoomId(roomId);
        room.setRoomNumber("101");

        Booking booking = new Booking();
        booking.setBookingId(1L);
        booking.setUser(user);
        booking.setRoom(room);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);

        // Mock behavior
        when(userService.getUserById(userId)).thenReturn(user);
        when(roomService.getRoomById(roomId)).thenReturn(room);
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(bookingDateRepository.saveAll(anySet())).thenReturn(Collections.emptyList());

        // Mock room availability check

        doReturn(true).when(spy(bookingService)).isRoomAvailable(roomId,startDate, endDate);



        // Execute the method under test
        Booking createdBooking = bookingService.createBooking(userId, roomId, startDate, endDate);

        // Assert the results
        assertNotNull(createdBooking);
        assertEquals(1L, createdBooking.getBookingId());
        assertEquals(user, createdBooking.getUser());
        assertEquals(room, createdBooking.getRoom());
        assertEquals(startDate, createdBooking.getStartDate());
        assertEquals(endDate, createdBooking.getEndDate());

        // Verify interactions
        verify(userService, times(1)).getUserById(userId);
        verify(roomService, times(1)).getRoomById(roomId);
        verify(bookingRepository, times(1)).save(any(Booking.class));
        verify(bookingDateRepository, times(1)).saveAll(anySet());
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
    void testGetBookingById() {

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

        List<Booking> expectedBookings = new ArrayList<>();

        expectedBookings.add(mockBooking);

        when(bookingRepository.findByUser(mockUser)).thenReturn(expectedBookings);

        List<Booking> actualBookings = bookingService.getBookingsByUser(mockUser);

        assertEquals(expectedBookings, actualBookings);

        verify(bookingRepository, times(1)).findByUser(mockUser);

    }

    @Test
    void deleteBookingById() {
        Long bookingId = mockBooking.getBookingId();

        bookingService.deleteBookingById(bookingId);

        verify(bookingRepository, times(1)).deleteById(bookingId);
    }
}