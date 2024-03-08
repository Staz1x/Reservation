package com.example.reservation2.services;

import com.example.reservation2.models.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRooms();

    Room getRoomById(Long id);

    Room createRoom(Room room);

    void deleteRoomById(Long id);
}
