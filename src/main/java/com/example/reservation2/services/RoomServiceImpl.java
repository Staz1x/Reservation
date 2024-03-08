package com.example.reservation2.services;

import com.example.reservation2.models.Room;
import com.example.reservation2.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{


    RoomRepository roomRepository;
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.getReferenceById(id);
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }
}
