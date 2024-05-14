package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@ToString
@Table(name = "bookings")
@Getter
@Setter
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BookingDate> bookingDates = new HashSet<>();

    private Timestamp bookingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;



    public static Booking fromString(String bookingId) {
        // Skapa en ny instans av Booking och returnera den
        Booking booking = new Booking();
        booking.setBookingId(Long.parseLong(bookingId));
        return booking;
    }


}
