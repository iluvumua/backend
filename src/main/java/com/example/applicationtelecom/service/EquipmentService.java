package com.example.applicationtelecom.service;

import com.example.applicationtelecom.entity.Equipment;
import com.example.applicationtelecom.entity.Building;
import com.example.applicationtelecom.entity.Meter;
import com.example.applicationtelecom.repository.EquipmentRepository;
import com.example.applicationtelecom.repository.BuildingRepository;
import com.example.applicationtelecom.repository.MeterRepository;
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

    @Autowired
    private MeterRepository meterRepository;

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> getEquipmentById(String id) {
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

    public Equipment updateEquipment(String id, Equipment equipment) {
        return equipmentRepository.findById(id).map(existing -> {
            existing.setName(equipment.getName());
            existing.setType(equipment.getType());
            existing.setState(equipment.getState());
            existing.setBuilding(equipment.getBuilding());
            existing.setMeter(equipment.getMeter());
            existing.setDesignation(equipment.getDesignation());
            existing.setTypeChassis(equipment.getTypeChassis());
            existing.setDescription(equipment.getDescription());
            existing.setCoordX(equipment.getCoordX());
            existing.setCoordY(equipment.getCoordY());
            existing.setLocation(equipment.getLocation());
            existing.setFournisseur(equipment.getFournisseur());
            existing.setStatus(equipment.getStatus());
            existing.setDateMiseEnService(equipment.getDateMiseEnService());
            existing.setLastUpdate(java.time.LocalDateTime.now().toString());
            existing.setVerifiedBy(equipment.getVerifiedBy());
            existing.setDateDemandeResiliation(equipment.getDateDemandeResiliation());
            existing.setDateResiliationEquipement(equipment.getDateResiliationEquipement());
            return equipmentRepository.save(existing);
        }).orElse(null);
    }

    public void deleteEquipment(String id) {
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

    // Associate equipment with meter
    public Equipment associateEquipmentWithMeter(String equipmentId, Long meterId) {
        Optional<Equipment> equipmentOpt = equipmentRepository.findById(equipmentId);
        Optional<Meter> meterOpt = meterRepository.findById(meterId);

        if (equipmentOpt.isPresent() && meterOpt.isPresent()) {
            Equipment equipment = equipmentOpt.get();
            Meter meter = meterOpt.get();
            equipment.setMeter(meter);
            equipment.setLastUpdate(java.time.LocalDateTime.now().toString());
            return equipmentRepository.save(equipment);
        }
        return null;
    }

    // Get equipment by meter ID
    public List<Equipment> getEquipmentByMeterId(String meterId) {
        return equipmentRepository.findAll().stream()
                .filter(equipment -> equipment.getMeter() != null && meterId.equals(equipment.getMeter().getId()))
                .toList();
    }

    // Validate equipment-meter association
    public boolean validateEquipmentMeterAssociation(String equipmentId, String meterId) {
        Optional<Equipment> equipmentOpt = equipmentRepository.findById(equipmentId);
        if (equipmentOpt.isPresent()) {
            Equipment equipment = equipmentOpt.get();
            return equipment.getMeter() != null && meterId.equals(equipment.getMeter().getId());
        }
        return false;
    }
}
