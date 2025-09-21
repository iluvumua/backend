package com.example.applicationtelecom.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompteur;
    private String policeNumber;
    private String description;
    private String typeTension;
    private String status;
    private String dateMiseEnService;
    private String lastUpdate;
    private String step1Data;
    private String step2Data;
    private String step3Data;

    @ManyToOne
    private Building building;

    @OneToMany(mappedBy = "meter")
    @JsonIgnore
    private java.util.List<Equipment> equipments;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCompteur() { return numeroCompteur; }
    public void setNumeroCompteur(String numeroCompteur) { this.numeroCompteur = numeroCompteur; }

    public String getPoliceNumber() { return policeNumber; }
    public void setPoliceNumber(String policeNumber) { this.policeNumber = policeNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTypeTension() { return typeTension; }
    public void setTypeTension(String typeTension) { this.typeTension = typeTension; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDateMiseEnService() { return dateMiseEnService; }
    public void setDateMiseEnService(String dateMiseEnService) { this.dateMiseEnService = dateMiseEnService; }

    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }

    public String getStep1Data() { return step1Data; }
    public void setStep1Data(String step1Data) { this.step1Data = step1Data; }

    public String getStep2Data() { return step2Data; }
    public void setStep2Data(String step2Data) { this.step2Data = step2Data; }

    public String getStep3Data() { return step3Data; }
    public void setStep3Data(String step3Data) { this.step3Data = step3Data; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }

    public java.util.List<Equipment> getEquipments() { return equipments; }
    public void setEquipments(java.util.List<Equipment> equipments) { this.equipments = equipments; }
}
