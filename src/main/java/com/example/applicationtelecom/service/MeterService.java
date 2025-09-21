package com.example.applicationtelecom.service;

import com.example.applicationtelecom.entity.Meter;
import com.example.applicationtelecom.entity.Equipment;
import com.example.applicationtelecom.entity.Building;
import com.example.applicationtelecom.repository.MeterRepository;
import com.example.applicationtelecom.repository.EquipmentRepository;
import com.example.applicationtelecom.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterService {

    @Autowired
    private MeterRepository meterRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    public Optional<Meter> getMeterById(Long id) {
        return meterRepository.findById(id);
    }

    public Meter saveMeter(Meter meter) {
        if (meter.getBuilding() != null) {
            // Check if building already has a meter
            Building building = meter.getBuilding();
            if (building.getMeter() != null) {
                throw new IllegalStateException("Building already has a meter assigned");
            }
        }
        return meterRepository.save(meter);
    }

    public void deleteMeter(Long id) {
        meterRepository.deleteById(id);
    }

    // Update step 1
    public Meter updateStep1(Long id, String data) {
        Optional<Meter> opt = getMeterById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep1Data(data);
            return meterRepository.save(meter);
        }
        return null;
    }

    // Update step 2
    public Meter updateStep2(Long id, String data) {
        Optional<Meter> opt = getMeterById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep2Data(data);
            meter.setNumeroCompteur(data); // assuming data is the numero
            return meterRepository.save(meter);
        }
        return null;
    }

    // Update step 3
    public Meter updateStep3(Long id, String data) {
        Optional<Meter> opt = getMeterById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep3Data(data);

            // Determine if meter is for building (indoor) or outdoor equipment
            boolean isBuildingMeter = meter.getBuilding() != null;
            boolean isOutdoorMeter = meter.getEquipments() != null && !meter.getEquipments().isEmpty();

            if (isBuildingMeter) {
                // Set meter and building state to "en service"
                meter.setStatus("en service");
                Building building = meter.getBuilding();
                buildingRepository.save(building);
            }

            if (isOutdoorMeter) {
                // Set meter and associated equipment state to "en service"
                meter.setStatus("en service");
                for (Equipment eq : meter.getEquipments()) {
                    eq.setState("en service");
                    equipmentRepository.save(eq);
                }
            }

            return meterRepository.save(meter);
        }
        return null;
    }
}
