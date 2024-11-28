package com.example.infraction.Repository;

import com.example.infraction.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    boolean existsByMatricule(String matricule);

    Voiture findByMatricule(String matricule);
}
