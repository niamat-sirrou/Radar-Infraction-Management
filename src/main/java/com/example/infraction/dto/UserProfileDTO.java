package com.example.infraction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private String username;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String country;
    private String adress;
    private String cin;
    private boolean active;
    private int points;
}

