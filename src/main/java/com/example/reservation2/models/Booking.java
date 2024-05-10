package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookingDate> bookingDates = new ArrayList<>();


    private Timestamp bookingDate;


    private Date startDate;

    private Date endDate;

    public static Booking fromString(String bookingId) {
        // Skapa en ny instans av Booking och returnera den
        Booking booking = new Booking();
        booking.setBookingId(Long.parseLong(bookingId));
        return booking;
    }


}
