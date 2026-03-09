package com.example.cicdassignment.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;

    // one-to-one relationship with Profile
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userProfileId")
//    private Profile profile;

    // one-to-many relationship with Workout
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Workout> workoutSessions = new ArrayList<>();

    // Constructors
    // JPA entity requires a default constructor with no params
    public User(){}

    public User(Long userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.profile = profile;
//        this.workoutSessions = workoutSessions;
    }

    // Getters
    public Long getUserId(){
        return userId;
    }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
//    public Profile getProfile(){ return profile; }
//    public List<Workout> getWorkouts(){ return workoutSessions; }

    // Setters
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setFirstName(String firstName){ this.firstName = firstName;  }
    public void setLastName(String lastName){ this.lastName = lastName;  }
//    public void setProfile(Profile profile){ this.profile = profile; }
//    public void setWorkout(List<Workout> workoutSessions){ this.workoutSessions = workoutSessions; }
}

