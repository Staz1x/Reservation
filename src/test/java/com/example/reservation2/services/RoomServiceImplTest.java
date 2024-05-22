package com.example.reservation2.services;

import com.example.reservation2.models.Room;
import com.example.reservation2.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void testGetAllRooms() {

        List<Room> rooms = Arrays.asList(
                new Room(1L, "101", BigDecimal.valueOf(100), "Single", 1),
                new Room(2L, "102", BigDecimal.valueOf(150), "Double", 2)
        );
        when(roomRepository.findAll()).thenReturn(rooms);

        List<Room> result = roomService.getAllRooms();

        assertEquals(2, result.size());
        assertEquals("101", result.get(0).getRoomNumber());
        assertEquals("102", result.get(1).getRoomNumber());
    }

    @Test
    public void testGetRoomById() {

        Room room = new Room(1L, "101", BigDecimal.valueOf(100), "Single", 1);
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Room result = roomService.getRoomById(1L);

        assertNotNull(result);
        assertEquals("101", result.getRoomNumber());
    }

    @Test
    public void testCreateRoom() {

        Room room = new Room(1L, "101", BigDecimal.valueOf(100), "Single", 1);
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(room);

        assertNotNull(createdRoom);
        assertEquals("101", createdRoom.getRoomNumber());
    }

    @Test
    public void testDeleteRoomById() {

        roomService.deleteRoomById(1L);

        verify(roomRepository).deleteById(1L);
    }
}