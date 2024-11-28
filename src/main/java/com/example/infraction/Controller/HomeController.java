package com.example.infraction.Controller;

import com.example.infraction.dto.UserDTO;
import com.example.infraction.dto.UserProfileDTO;
import com.example.infraction.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/accueil")
    public UserDTO getUserDetails(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Utilisateur non connecté");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setFullname(user.getFullname());
        userDTO.setPoints(user.getPermis().getPoints());

        return userDTO;
    }
    @GetMapping("/profil")
    public UserProfileDTO getUserProfile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Utilisateur non connecté");
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername(user.getUsername());
        userProfileDTO.setFullname(user.getFullname());
        userProfileDTO.setEmail(user.getEmail());
        userProfileDTO.setPhoneNumber(user.getPhoneNumber());
        userProfileDTO.setCountry(user.getCountry());
        userProfileDTO.setAdress(user.getAdress());
        userProfileDTO.setCin(user.getCin());
        userProfileDTO.setActive(user.isActive());
        userProfileDTO.setPoints(user.getPermis().getPoints());

        return userProfileDTO;
    }

}

