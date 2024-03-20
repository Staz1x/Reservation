package com.example.reservation2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;

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


    private Timestamp bookingDate;


    private Date startDate;

    private Date endDate;


}
