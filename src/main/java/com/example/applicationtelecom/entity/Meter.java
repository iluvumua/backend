package com.example.applicationtelecom.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCompteur = "0000000";
    private String state = "en cours"; // en cours, en service

    // Step data
    private String step1Data;
    private String step2Data;
    private String step3Data;

    @OneToOne
    private Building building;

    @OneToMany(mappedBy = "meter")
    private List<Equipment> equipments;

    @OneToMany(mappedBy = "meter")
    private List<Bill> bills;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCompteur() { return numeroCompteur; }
    public void setNumeroCompteur(String numeroCompteur) { this.numeroCompteur = numeroCompteur; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getStep1Data() { return step1Data; }
    public void setStep1Data(String step1Data) { this.step1Data = step1Data; }

    public String getStep2Data() { return step2Data; }
    public void setStep2Data(String step2Data) { this.step2Data = step2Data; }

    public String getStep3Data() { return step3Data; }
    public void setStep3Data(String step3Data) { this.step3Data = step3Data; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }

    public List<Equipment> getEquipments() { return equipments; }
    public void setEquipments(List<Equipment> equipments) { this.equipments = equipments; }

    public List<Bill> getBills() { return bills; }
    public void setBills(List<Bill> bills) { this.bills = bills; }
}
