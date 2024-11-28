package com.example.infraction.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Voiture {
    @Id

    private Long id;
    private String marque;
    private String matricule;
    private String modele;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
