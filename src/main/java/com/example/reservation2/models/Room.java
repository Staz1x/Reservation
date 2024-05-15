package com.example.reservation2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString
@Table(name = "Rooms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roomId;

    private String roomNumber;

    private BigDecimal price;

    private String roomType;

    private int capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Booking> bookings = new HashSet<>();

//    public static Room fromString(String roomId){
//        Room room = new Room();
//        room.setRoomId(Long.parseLong(roomId));
//        return room;
//    }


}
