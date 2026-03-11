package com.example.cicdassignment.Repository;

import com.example.cicdassignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}