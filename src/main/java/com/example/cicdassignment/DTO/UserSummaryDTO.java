package com.example.cicdassignment.DTO;

public class UserSummaryDTO {
    // Fields
    private Long userId;
    private String firstName;
    private String lastName;

    // Constructors
    public UserSummaryDTO(){}

    public UserSummaryDTO(Long userId, String firstName, String lastName){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters
    public Long getUserId(){ return userId; }
    public String getFirstName(){ return firstName; }
    public String getLastName(){ return lastName; }

    // Setters
    public void setUserId(Long userId){ this.userId = userId; }
    public void setFirstName(String firstName){ this.firstName = firstName; }
    public void setLastName(String lastName){ this.lastName = lastName; }
}
