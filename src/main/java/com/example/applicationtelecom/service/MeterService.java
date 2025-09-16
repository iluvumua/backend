package com.example.applicationtelecom.service;

import com.example.applicationtelecom.entity.Meter;
import com.example.applicationtelecom.entity.Equipment;
import com.example.applicationtelecom.repository.MeterRepository;
import com.example.applicationtelecom.repository.EquipmentRepository;
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

    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }

    public Optional<Meter> getMeterById(String id) {
        return meterRepository.findById(id);
    }

    public Meter saveMeter(Meter meter) {
        return meterRepository.save(meter);
    }

    public void deleteMeter(String id) {
        meterRepository.deleteById(id);
    }

    // Update step 1
    public Meter updateStep1(String id, String data) {
        Optional<Meter> opt = meterRepository.findById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep1Data(data);
            return meterRepository.save(meter);
        }
        return null;
    }

    // Update step 2
    public Meter updateStep2(String id, String data) {
        Optional<Meter> opt = meterRepository.findById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep2Data(data);
            meter.setNumeroCompteur(data); // assuming data is the numero
            return meterRepository.save(meter);
        }
        return null;
    }

    // Update step 3
    public Meter updateStep3(String id, String data) {
        Optional<Meter> opt = meterRepository.findById(id);
        if (opt.isPresent()) {
            Meter meter = opt.get();
            meter.setStep3Data(data);
            meter.setState("en service");
            // If for building, building is already associated
            // If for equipment, set equipment state to en service
            if (meter.getEquipments() != null) {
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
