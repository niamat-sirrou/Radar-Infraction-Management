package com.example.infraction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private Instant creation;
    private Instant expire;
    private boolean activation ;
    private String code;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private User user;
}
