package com.example.cicdassignment.Repository;

import com.example.cicdassignment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
