package com.example.cicdassignment.unit;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    saveUser_persistsUser
    searchUsersByFirstName_returnsResults
    searchUsersByLastName_returnsResults
    getUserById_returnsUserDTO_whenUserExists
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
}
