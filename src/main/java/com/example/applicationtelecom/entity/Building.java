package com.example.applicationtelecom.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String code;
    private String commune;
    private String localisation;
    @ElementCollection
    private List<String> nature = new ArrayList<>();
    private String propriete;

    @OneToOne
    private Meter meter;

    @OneToMany(mappedBy = "building")
    private List<Equipment> equipments;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCommune() { return commune; }
    public void setCommune(String commune) { this.commune = commune; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }

    public List<String> getNature() { return nature; }
    public void setNature(List<String> nature) { this.nature = nature; }

    public String getPropriete() { return propriete; }
    public void setPropriete(String propriete) { this.propriete = propriete; }

    public Meter getMeter() { return meter; }
    public void setMeter(Meter meter) { this.meter = meter; }

    public List<Equipment> getEquipments() { return equipments; }
    public void setEquipments(List<Equipment> equipments) { this.equipments = equipments; }
}
