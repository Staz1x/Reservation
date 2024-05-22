package com.example.reservation2.services;

import com.example.reservation2.enums.UserRole;
import com.example.reservation2.models.Role;
import com.example.reservation2.models.User;
import com.example.reservation2.repositories.RoleRepository;
import com.example.reservation2.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl userService;


    @Mock
    User mockUser;

    @Mock

    Role mockRole;


    @BeforeEach
    public void setup(){
        mockUser = new User();
        mockRole = new Role();

        mockRole.setRoleId(1L);
        mockRole.setRoleName(UserRole.USER);


        mockUser.setUserId(1L);
        mockUser.setEmail("test@testmail.se");
        mockUser.setFirstName("Teston");
        mockUser.setLastName("Testsson");
        mockUser.setRole(mockRole);
        mockUser.setPassword("123456");
        mockUser.setPhoneNr("0703000000");
    }

    @Test
    void getUserById() {

        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.ofNullable(mockUser));

        User trueUser = userService.getUserById(userId);

        assertEquals(trueUser, mockUser);

        verify(userRepository, times(1)).findById(userId);

    }

    @Test
    void findUserByEmail() {

        String email = "test@testmai.test";

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        User trueUser = userService.findUserByEmail(email);

        assertEquals(trueUser, mockUser);

        verify(userRepository, times(1)).findByEmail(email);

    }

    @Test
    void createUser() {
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(roleRepository.getReferenceById(2L)).thenReturn(mockRole);

        User createdUser = userService.createUser(mockUser);

        assertEquals(createdUser, mockUser);
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void getAllUsers() {

        List<User> mockUserList = new ArrayList<>();
        mockUserList.add(mockUser);

        when(userRepository.findAll()).thenReturn(mockUserList);

        List<User> actualUsers = userService.getAllUsers();

        assertEquals(actualUsers, mockUserList);

        verify(userRepository, times(1)).findAll();

    }
}