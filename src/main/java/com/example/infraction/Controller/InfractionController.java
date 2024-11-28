package com.example.infraction.Controller;

import com.example.infraction.dto.InfractionDTO;
import com.example.infraction.model.User;
import com.example.infraction.service.InfractionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/infractions")
public class InfractionController {

    @Autowired
    private InfractionService infractionService;

    @GetMapping("/mes-infractions")
    public List<InfractionDTO> getUserInfractions(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("Utilisateur non connecté");
        }
        return infractionService.getInfractionsByUser(user);
    }
}


