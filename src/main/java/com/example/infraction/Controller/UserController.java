package com.example.infraction.Controller;

import com.example.infraction.dto.AuthentificationDto;
import com.example.infraction.model.User;
import com.example.infraction.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/inscription")
    public ResponseEntity<String> inscription(@RequestBody User user) {
        try {
            log.info("Inscription effectuée");
            userService.inscription(user);
            return ResponseEntity.ok("Inscription successful!");
        } catch (Exception e) {
            log.error("Error during inscription: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during inscription: " + e.getMessage());
        }
    }

    @PostMapping(path = "/activation")
    public void activation(@RequestBody Map<String, String> activation) {
        this.userService.activation(activation);
    }

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, String>> connexion(@RequestBody AuthentificationDto authentificationDto, HttpSession session) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationDto.username(), authentificationDto.password())
            );

            if (authenticate.isAuthenticated()) {
                log.info("Connexion effectuée pour l'utilisateur: {}", authentificationDto.username());

                User user = (User) userService.loadUserByUsername(authentificationDto.username());
                if (user == null) {
                    throw new RuntimeException("Utilisateur non trouvé");
                }

                session.setAttribute("user", user);

                Map<String, String> response = new HashMap<>();
                response.put("message", "Authentication successful");
                return ResponseEntity.ok(response);
            } else {
                log.warn("Authentication failed for user: " + authentificationDto.username());
                Map<String, String> response = new HashMap<>();
                response.put("message", "Authentication failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (AuthenticationException e) {
            log.error("Authentication error: " + e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Authentication error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
