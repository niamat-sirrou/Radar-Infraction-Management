package com.example.infraction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class permis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int points;
    private LocalDate dateDebutPermis;
    private boolean passeDeuxAnnees = false;
    private String cin;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}

