package com.example.infraction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Infraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amende;
    private String numeroRadar;
    private int vitesse;
    private int points;
    private LocalDate dateInfraction;
    private String lieuInfraction;

    @ManyToOne
    private  Voiture voiture;
}
