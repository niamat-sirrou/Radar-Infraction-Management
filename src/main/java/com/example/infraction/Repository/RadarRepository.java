package com.example.infraction.Repository;

import com.example.infraction.model.Radar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RadarRepository extends JpaRepository<Radar, Long> {
}
