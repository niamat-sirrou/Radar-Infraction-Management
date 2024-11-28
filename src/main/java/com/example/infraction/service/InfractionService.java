package com.example.infraction.service;

import com.example.infraction.dto.InfractionDTO;
import com.example.infraction.model.Infraction;
import com.example.infraction.model.User;
import com.example.infraction.Repository.InfractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfractionService {

    @Autowired
    private InfractionRepository infractionRepository;


    public Optional<Infraction> getInfraction(long id) {

        return infractionRepository.findById(id);
    }

    public List<Infraction> getInfractions() {
        return infractionRepository.findAll();
    }


    public List<InfractionDTO> getInfractionsByUser(User user) {
        List<Infraction> infractions = infractionRepository.findByVoiture_User(user);
        return infractions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private InfractionDTO convertToDto(Infraction infraction) {
        InfractionDTO infractionDTO = new InfractionDTO();
        infractionDTO.setDateInfraction(infraction.getDateInfraction());
        infractionDTO.setLieuInfraction(infraction.getLieuInfraction());
        infractionDTO.setVitesse(infraction.getVitesse());
        infractionDTO.setPoints(infraction.getPoints());
        infractionDTO.setAmende(infraction.getAmende());
        infractionDTO.setNumeroRadar(infraction.getNumeroRadar());
        infractionDTO.setMatricule(infraction.getVoiture().getMatricule());
        return infractionDTO;
    }
}


