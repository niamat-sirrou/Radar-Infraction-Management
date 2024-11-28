package com.example.infraction.service;

import com.example.infraction.Repository.*;
import com.example.infraction.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RadarService {
    @Autowired
    private RadarRepository radarRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VoitureRepository voitureRepository;
    @Autowired
    InfractionRepository infractionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PermisRepository permisRepository;

    public List<Radar> getAllRadars() {
        return radarRepository.findAll();
    }

    public Radar getRadarById(Long id) {
        return radarRepository.findById(id).orElse(null);
    }

    public Radar saveRadar(Radar radar) {
        return radarRepository.save(radar);
    }

    public void deleteRadar(Long id) {
        radarRepository.deleteById(id);
    }


    public Radar saveRadarAndSendNotification(Radar radar) {
        // Enregistre le radar dans la base de données
        Radar radarSaved = radarRepository.save(radar);
        // Vérifie si le matricule du radar existe dans la table Voiture
        if (matriculeExists(radar.getMatricule())) {
            // Si le matricule existe, envoie une notification au propriétaire de la voiture
            boolean emailSent = sendNotificationToOwner(radar.getMatricule());
            if (emailSent) {
                System.out.println("Notification envoyée avec succès !");
            } else {
                System.out.println("Échec de l'envoi de la notification.");
            }
            int amende = calculerAmende(radar.getVitesseDetectee(), radar.getVitesseMaxAutorisee());
            if (amende > 0) {
                Infraction infraction = new Infraction();
                infraction.setAmende(amende);
                infraction.setNumeroRadar(String.valueOf(radar.getNumRadar()));
                infraction.setVitesse(radar.getVitesseDetectee());
                infraction.setDateInfraction(radar.getDateDetectee()); // Utiliser la date actuelle
                infraction.setLieuInfraction(radar.getAdresse());


                // Obtenez la voiture associée au radar et l'associez à l'infraction
                Voiture voiture = voitureRepository.findByMatricule(radar.getMatricule());
                infraction.setVoiture(voiture);
                mettreAJourPointsEtInfraction(infraction, radar);

                // Enregistrez l'infraction dans la base de données
                infractionRepository.save(infraction);
            }

        }
        return radarSaved;
    }

    public boolean matriculeExists(String matricule) {
        // Vérifie si le matricule existe dans la table Voiture
        return voitureRepository.existsByMatricule(matricule);
    }
    private int calculerAmende(double vitesseDetectee, double vitesseMaxAutorisee) {
        double differenceVitesse = vitesseDetectee - vitesseMaxAutorisee;

        if (differenceVitesse < 20) {
            return 300;
        } else if (differenceVitesse < 30) {
            return 500;
        } else if (differenceVitesse < 50) {
            return 700;
        } else {
            return 0; // Par exemple, retourner 0 pour une amende non spécifiée
        }
    }

    public boolean sendNotificationToOwner(String matricule) {
        // Récupère le propriétaire de la voiture associée au matricule
        Voiture voiture = voitureRepository.findByMatricule(matricule);
        if (voiture != null) {
            User proprietaire = voiture.getUser();
            if (proprietaire != null) {
                String emailProprietaire = proprietaire.getEmail();
                if (emailProprietaire != null && !emailProprietaire.isEmpty()) {
                    // Envoie une notification par e-mail au propriétaire de la voiture
                    String sujet = "Notification d'infraction radar";
                    String contenu = "Votre voiture avec le matricule " + matricule + " a été détectée par un radar en infraction.";
                    return emailService. envoyerEmail(emailProprietaire, sujet, contenu);
                }
            }
        }
        return false; // Retourne false si l'e-mail n'a pas pu être envoyé
    }



    private void mettreAJourPointsEtInfraction(Infraction infraction, Radar radar) {
        Voiture voiture = infraction.getVoiture();
        User proprietaire = voiture.getUser();

        if (proprietaire != null) {
            permis permis = permisRepository.findByUserCin(proprietaire.getCin());
            if (permis != null) {

                if (ChronoUnit.YEARS.between(permis.getDateDebutPermis(), LocalDate.now()) >= 2) {
                    if (!permis.isPasseDeuxAnnees()) {
                        permis.setPasseDeuxAnnees(true);
                        permis.setPoints(Math.min(permis.getPoints() + 10, 30)); // Maximum 30 points
                    }
                }

                int pointsPerdus = calculerPoints(radar.getVitesseDetectee(), radar.getVitesseMaxAutorisee());
                int pointsRestants;

                if (pointsPerdus > permis.getPoints()) {
                    pointsRestants = 0;
                } else {
                    pointsRestants = permis.getPoints() - pointsPerdus;
                }

                permis.setPoints(pointsRestants);
                permisRepository.save(permis);

                infraction.setPoints(pointsRestants);

                if (pointsRestants < 6) {
                    String email = proprietaire.getEmail();
                    String subject = "Avertissement de points de permis";
                    String text = "Attention " + proprietaire.getFullname() + ",\n\n" +
                            "Votre nombre de points de permis est inférieur à 6. Veuillez conduire prudemment pour éviter de perdre votre permis.\n\n" +
                            "Cordialement,\n" +
                            "Service de Sécurité Routière";

                    emailService.envoyerEmail(email, subject, text);
                }
            }
        }
    }
    private int calculerPoints(double vitesseDetectee, double vitesseMaxAutorisee) {
        double differenceVitesse = vitesseDetectee - vitesseMaxAutorisee;
        int pointsPerdus = 0;

        if (differenceVitesse < 20) {
            pointsPerdus = 1;
        } else if (differenceVitesse < 30) {
            pointsPerdus = 3;
        } else if (differenceVitesse < 50) {
            pointsPerdus = 5;
        } else {
            pointsPerdus = 10;
        }

        return pointsPerdus;
    }



}
