package com.example.cicdassignment.integration;

import com.example.cicdassignment.DTO.UserDTO;
import com.example.cicdassignment.Entity.User;
import com.example.cicdassignment.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {
    // integration test
    /*
    Examples
    createUser_persistsUser
    findByFirstName_returnsCorrectUser
    getUserById_returnsCorrectUser
    deleteUser_removesUserFromDatabase
    * */

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired

    private UserRepository userRepository;

    private User savedUser;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();

        userRepository.save(new User(null, "James", "Talbot"));
        userRepository.save(new User(null, "Tim", "Tester"));
        userRepository.save(new User(null, "John", "Doe"));

        savedUser = userRepository.save(new User(null, "Another", "Tester"));
    }

    @Test
    @DisplayName("create user - persist user")
    void createUser_shouldPersistUser(){
        String url = "http://localhost:"+port+"/users";

        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(url, UserDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3);
    }

    @Test
    @DisplayName("find user by first name - returns correct user")
    void findByFirstName_returnsCorrectUser(){
        String firstName = savedUser.getFirstName();
        String url = "http://localhost:"+port+"/users/search?firstName="+firstName;

        ResponseEntity<UserDTO[]> response = restTemplate.getForEntity(url, UserDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getUserId()).isEqualTo(savedUser.getUserId());
        assertThat(response.getBody()[0].getFirstName()).isEqualTo(savedUser.getFirstName());
        assertThat(response.getBody()[0].getLastName()).isEqualTo(savedUser.getLastName());
    }

    @Test
    @DisplayName("get user by id - returns correct user")
    void getUserById_returnsCorrectUser(){
        Long userId = savedUser.getUserId();
        String url = "http://localhost:"+port+"/users/"+savedUser.getUserId();

        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserId()).isEqualTo(userId);
        assertThat(response.getBody().getFirstName()).isEqualTo(savedUser.getFirstName());
        assertThat(response.getBody().getLastName()).isEqualTo(savedUser.getLastName());
    }

    @Test
    @DisplayName("delete user - remove from db")
    void deleteUser_removesUserFromDatabase(){
        User user = userRepository.findAll().get(0);
        String url = "http://localhost:"+port+"/users/"+user.getUserId();

        restTemplate.delete(url);

        assertThat(userRepository.existsById(user.getUserId())).isFalse();
    }
}
