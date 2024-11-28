package com.example.infraction.Repository;

import com.example.infraction.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValidationRepository extends JpaRepository<Validation, Long> {

 Optional<Validation> findByCode(String code);
}
