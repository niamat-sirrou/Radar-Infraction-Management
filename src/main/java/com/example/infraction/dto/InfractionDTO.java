package com.example.infraction.dto;

import java.time.LocalDate;

public class InfractionDTO {
    private LocalDate dateInfraction;
    private String lieuInfraction;
    private int vitesse;
    private int points;
    private double amende;
    private String numeroRadar;
    private String matricule;

    // Getters and Setters

    public LocalDate getDateInfraction() {
        return dateInfraction;
    }

    public void setDateInfraction(LocalDate dateInfraction) {
        this.dateInfraction = dateInfraction;
    }

    public String getLieuInfraction() {
        return lieuInfraction;
    }

    public void setLieuInfraction(String lieuInfraction) {
        this.lieuInfraction = lieuInfraction;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getAmende() {
        return amende;
    }

    public void setAmende(double amende) {
        this.amende = amende;
    }

    public String getNumeroRadar() {
        return numeroRadar;
    }

    public void setNumeroRadar(String numeroRadar) {
        this.numeroRadar = numeroRadar;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
