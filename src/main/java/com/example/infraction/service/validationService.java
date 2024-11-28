package com.example.infraction.service;

import com.example.infraction.Repository.ValidationRepository;
import com.example.infraction.model.Payment;
import com.example.infraction.model.User;
import com.example.infraction.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
public class validationService {
    @Autowired
    private ValidationRepository validationRepository;
    @Autowired
    private notificationService notificationservice;

    public void enregistrer(Payment payment) {
        Validation validation = new Validation();
        validation.setPayment(payment);
        Instant creation = Instant.now();
        validation.setCreation(creation);

        Instant expiration =creation.plus(10,MINUTES);
        validation.setExpire(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);
        this.validationRepository.save(validation);

        this.notificationservice.envoyer(validation);

    }
    public boolean verifierCode(String code) {
        Optional<Validation> optionalValidation = validationRepository.findByCode(code);
        if (optionalValidation.isEmpty()) {
            return false;
        }
        Validation validation = optionalValidation.get();
        if (validation.getExpire().isBefore(Instant.now())) {
            return false;
        }
        validation.setActivation(true);
        validationRepository.save(validation);
        return true;
    }

    public void enregistrerU(User user) {
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreation(creation);

        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpire(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);
        validation.setCode(code);
        this.validationRepository.save(validation);

        this.notificationservice.envoyer(validation);
    }
    public  Validation lirefonctionDuCode(String code){
        return   this.validationRepository.findByCode(code).orElseThrow(()->new RuntimeException("votre code est invalid"));
    }
}
