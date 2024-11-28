package com.example.infraction;

import com.example.infraction.Repository.UserRepository;
import com.example.infraction.model.Role;
import com.example.infraction.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@SpringBootApplication
public class InfractionApplication implements CommandLineRunner {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(InfractionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User radar = User.builder()
                .active(true)
                .fullname("Radar")
                .username("radar")
                .password(passwordEncoder.encode("radar"))
                .email("radar@g.com")
                .role(
                        Role.builder()
                                .libelle(TypeDeRole.RADAR)
                                .build()
                )
                .build();
        this.userRepository.findByEmail(radar.getEmail())
                .orElse(this.userRepository.save(radar));


    }
}