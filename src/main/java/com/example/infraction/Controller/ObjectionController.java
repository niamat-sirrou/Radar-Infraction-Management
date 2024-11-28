package com.example.infraction.Controller;

import com.example.infraction.Repository.UserRepository;
import com.example.infraction.model.ObjectionRequest;
import com.example.infraction.model.User;
import com.example.infraction.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/objection")
public class ObjectionController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> submitObjection(@RequestBody ObjectionRequest objectionRequest) {
        try {
            User user = userRepository.findById(objectionRequest.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            emailService.sendObjectionEmail(user.getEmail(), objectionRequest);
            return new ResponseEntity<>("Objection sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send objection: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}