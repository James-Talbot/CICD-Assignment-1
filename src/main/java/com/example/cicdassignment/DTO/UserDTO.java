package com.example.cicdassignment.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UserDTO {
    // Fields
    @Valid

    private Long userId;
    @NotNull(message = "User first name is mandatory")
    @NotBlank(message = "User first name cannot be blank")
    private String firstName;
    @NotNull(message = "User last name is mandatory")
    @NotBlank(message = "User last name cannot be blank")
    private String lastName;
//    private ProfileDTO profile;
//    private List<WorkoutDTO> workouts;

    // Constructors
    public UserDTO(){}

    public UserDTO(Long userId, String firstName, String lastName){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
//        this.profile = profile;
//        this.workouts = workouts;
    }

    // Getters
    public Long getUserId(){ return userId; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }
//    public ProfileDTO getProfile(){ return profile; }
//    public List<WorkoutDTO> getWorkouts(){ return workouts; }

    // Setters
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public void setFirstName(String firstName){ this.firstName = firstName;  }
    public void setLastName(String lastName){ this.lastName = lastName;  }
//    public void setProfile(ProfileDTO profile){ this.profile = profile; }
//    public void setWorkouts(List<WorkoutDTO> workouts){ this.workouts = workouts; }
}

