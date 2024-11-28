package com.example.infraction.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ObjectionRequest {
    private Long userId;
    private String userEmail;
    private String subject;
    private String message;
}
