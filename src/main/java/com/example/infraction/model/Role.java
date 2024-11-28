package com.example.infraction.model;

import com.example.infraction.TypeDeRole;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeDeRole libelle;
}
