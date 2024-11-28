package com.example.infraction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double Amount;

    @OneToOne
    @JoinColumn(name = "infraction_id")
    private Infraction infraction;

    private LocalDate datePayment;
    private boolean paid ;

}
