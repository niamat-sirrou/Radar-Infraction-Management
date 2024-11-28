package com.example.infraction.Repository;

import com.example.infraction.model.Infraction;
import com.example.infraction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfractionRepository extends JpaRepository<Infraction, Long> {
    List<Infraction> findByVoiture_User(User user);
}

