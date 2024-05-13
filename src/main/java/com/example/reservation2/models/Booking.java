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
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    private Set<BookingDate> bookingDates;

    private Timestamp bookingDate;



    public static Booking fromString(String bookingId) {
        // Skapa en ny instans av Booking och returnera den
        Booking booking = new Booking();
        booking.setBookingId(Long.parseLong(bookingId));
        return booking;
    }


}
