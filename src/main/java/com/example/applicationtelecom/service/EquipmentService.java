package com.example.applicationtelecom.service;

import com.example.applicationtelecom.entity.Equipment;
import com.example.applicationtelecom.entity.Building;
import com.example.applicationtelecom.repository.EquipmentRepository;
import com.example.applicationtelecom.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    public Equipment saveEquipment(Equipment equipment) {
        // Logic based on workflow
        if ("indoor".equals(equipment.getType())) {
            equipment.setState("en service");
        } else if ("outdoor".equals(equipment.getType())) {
            equipment.setState("en cours");
        }
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    // Add equipment to building (indoor)
    public Equipment addEquipmentToBuilding(Long buildingId, Equipment equipment) {
        Optional<Building> buildingOpt = buildingRepository.findById(buildingId);
        if (buildingOpt.isPresent()) {
            Building building = buildingOpt.get();
            equipment.setBuilding(building);
            equipment.setType("indoor");
            equipment.setState("en service");
            return equipmentRepository.save(equipment);
        }
        return null;
    }
}
