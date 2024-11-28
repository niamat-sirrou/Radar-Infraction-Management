package com.example.infraction.service;

import com.example.infraction.model.ObjectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean envoyerEmail(String destinataire, String sujet, String contenu) {
        try {
            // Envoie un e-mail au destinataire avec le sujet et le contenu spécifiés
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinataire);
            message.setSubject(sujet);
            message.setText(contenu);
            javaMailSender.send(message);
            return true; // Retourne true si l'e-mail a été envoyé avec succès
        } catch (MailException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'échec de l'envoi de l'e-mail
        }
    }
    public void sendObjectionEmail(String userEmail, ObjectionRequest objectionRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("fatihabr3355@gmail.com");
        message.setSubject("Objection: " + objectionRequest.getSubject());
        message.setText("From: " + userEmail + "\n\n" + objectionRequest.getMessage());

        javaMailSender.send(message);
    }


}
