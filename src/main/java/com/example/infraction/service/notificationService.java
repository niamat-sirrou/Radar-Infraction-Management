package com.example.infraction.service;

import com.example.infraction.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class notificationService {
    @Autowired
    JavaMailSender javaMailSender;
    public void envoyer(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fatihaberrami1@gmail.com");
       // message.setTo(validation.getPayment().getBankAccount().getEmail());
        message.setTo(validation.getUser().getEmail());
        message.setSubject("votre code de activation");
        String text=String.format("bonjour, <br> votre code d'activation %s a bientot", validation.getCode());
        message.setText(text);

        javaMailSender.send(message);

    }
}
