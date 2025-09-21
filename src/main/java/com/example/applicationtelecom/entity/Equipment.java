package com.example.applicationtelecom.entity;

import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    private String id;

    private String name;
    private String type; // indoor, outdoor
    private String state = "en cours"; // en cours, en service

    private String designation;
    private String typeChassis;
    private String description;
    private Double coordX;
    private Double coordY;
    private String location;
    private String fournisseur;
    private String status;
    private String dateMiseEnService;
    private String lastUpdate;
    private String verifiedBy;
    private String dateDemandeResiliation;
    private String dateResiliationEquipement;

    @ManyToOne
    private Building building;

    @ManyToOne
    private Meter meter;

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getTypeChassis() { return typeChassis; }
    public void setTypeChassis(String typeChassis) { this.typeChassis = typeChassis; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getCoordX() { return coordX; }
    public void setCoordX(Double coordX) { this.coordX = coordX; }

    public Double getCoordY() { return coordY; }
    public void setCoordY(Double coordY) { this.coordY = coordY; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getFournisseur() { return fournisseur; }
    public void setFournisseur(String fournisseur) { this.fournisseur = fournisseur; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDateMiseEnService() { return dateMiseEnService; }
    public void setDateMiseEnService(String dateMiseEnService) { this.dateMiseEnService = dateMiseEnService; }

    public String getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(String lastUpdate) { this.lastUpdate = lastUpdate; }

    public String getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(String verifiedBy) { this.verifiedBy = verifiedBy; }

    public String getDateDemandeResiliation() { return dateDemandeResiliation; }
    public void setDateDemandeResiliation(String dateDemandeResiliation) { this.dateDemandeResiliation = dateDemandeResiliation; }

    public String getDateResiliationEquipement() { return dateResiliationEquipement; }
    public void setDateResiliationEquipement(String dateResiliationEquipement) { this.dateResiliationEquipement = dateResiliationEquipement; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }

    public Meter getMeter() { return meter; }
    public void setMeter(Meter meter) { this.meter = meter; }
}
