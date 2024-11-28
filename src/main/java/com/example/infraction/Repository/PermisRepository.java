package com.example.infraction.Repository;

import com.example.infraction.model.permis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisRepository extends JpaRepository<permis, Long> {
    permis findByUserCin(String cin);
}
