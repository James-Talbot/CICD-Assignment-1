package com.example.cicdassignment.unit;

import com.example.cicdassignment.DTO.UserDTO;
import com.example.cicdassignment.DTO.UserSummaryDTO;
import com.example.cicdassignment.Entity.User;
import com.example.cicdassignment.Repository.UserRepository;
import com.example.cicdassignment.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Unit Tests")
public class UserServiceTest {
    // unit tests using JUnit + Mockito
    /*
    Examples
    getAllUsers
    getAllUsers_WhenNoUserExists
    getUserById_returnsUserDTO_whenUserExists
    searchUsersByFirstName_returnsResults
    searchUsersByLastName_returnsResults
    saveUser_persistsUser
    getUserById_throwsException_whenUserNotFound
    deleteUser_removesUser
    */

    @Mock
    private UserRepository userRepository;

    // class we want to test
    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("get all users - return list")
    void getAllUsers(){
        // given
        Pageable pageable = PageRequest.of(0, 3);
        User user1 = new User(1L, "James", "Talbot");
        User user2 = new User(2L, "Tim", "Tester");
        List<User> users = List.of(user1, user2);
        Page<User> page = new PageImpl<>(users);
        when(userRepository.findAll(pageable)).thenReturn(page);

        // when
        List<UserSummaryDTO> result = userService.findAll(pageable);

        // then
        assertEquals(2, result.size());
        assertEquals("James", result.get(0).getFirstName());
        assertEquals("Talbot", result.get(0).getLastName());
        assertEquals("Tim", result.get(1).getFirstName());
        assertEquals("Tester", result.get(1).getLastName());

        verify(userRepository).findAll(pageable);
    }

    @Test
    @DisplayName("get all users - when no users exist")
    void getAllUsers_WhenNoUsersExist(){
        // given
        Pageable pageable = PageRequest.of(0, 3);
        Page<User> emptyPage = new PageImpl<>(List.of());
        when(userRepository.findAll(pageable)).thenReturn(emptyPage);

        // when
        List<UserSummaryDTO> result = userService.findAll(pageable);

        // then
        assertTrue(result.isEmpty());

        verify(userRepository).findAll(pageable);
    }

    @Test
    @DisplayName("get user by id")
    void getUserById_returnsUserDTO_whenUserExists(){
        // given
        Long userId = 1L;
        User user = new User(userId, "James", "Talbot");
        when(userRepository.findById(userId)).thenReturn(Optional.of((user)));

        // when
        UserDTO result = userService.getUserById(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("James", result.getFirstName());
        assertEquals("Talbot", result.getLastName());

        verify(userRepository).findById(userId);
    }

    @Test
    @DisplayName("search for user by first name")
    void searchUsersByFirstName_returnsResults(){
        // given
        String firstname = "James";
        User user = new User(1L, "James", "Talbot");
        when(userRepository.findByFirstName(firstname)).thenReturn(List.of(user));

        // when
        List<UserDTO> results = userService.getUsersByName(firstname, null);

        // then
        assertEquals(1, results.size());
        assertEquals("James", results.get(0).getFirstName());

        verify(userRepository).findByFirstName(firstname);
    }

    @Test
    @DisplayName("search for user by last name")
    void searchUsersByLastName_returnsResults(){
        // given
        String lastname = "Talbot";
        User user = new User(1L, "James", "Talbot");
        when(userRepository.findByLastName(lastname)).thenReturn(List.of(user));

        // when
        List<UserDTO> result = userService.getUsersByName(null, lastname);

        // then
        assertEquals(1, result.size());
        assertEquals("Talbot", result.get(0).getLastName());

        verify(userRepository).findByLastName(lastname);
    }
}
