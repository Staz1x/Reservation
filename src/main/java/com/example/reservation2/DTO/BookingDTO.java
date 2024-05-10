package com.example.reservation2.DTO;

import java.util.Date;
import java.util.List;

public class BookingDTO {

    private Long bookingId;
    private Date bookingDate;
    private Date startDate;
    private Date endDate;
    private UserDTO user;
    private List<BookingDateDTO> bookingDates;
}
