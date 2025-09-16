package com.example.applicationtelecom.entity;

import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // indoor, outdoor
    private String state = "en cours"; // en cours, en service

    @ManyToOne
    private Building building;

    @ManyToOne
    private Meter meter;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public Building getBuilding() { return building; }
    public void setBuilding(Building building) { this.building = building; }

    public Meter getMeter() { return meter; }
    public void setMeter(Meter meter) { this.meter = meter; }
}
