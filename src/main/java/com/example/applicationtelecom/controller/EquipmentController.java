package com.example.applicationtelecom.controller;

import com.example.applicationtelecom.entity.Equipment;
import com.example.applicationtelecom.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
@CrossOrigin(origins = "http://localhost:9002")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public List<Equipment> getAllEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable String id) {
        return equipmentService.getEquipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable String id, @RequestBody Equipment equipment) {
        Equipment updated = equipmentService.updateEquipment(id, equipment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PostMapping("/building/{buildingId}")
    public ResponseEntity<Equipment> addEquipmentToBuilding(@PathVariable Long buildingId, @RequestBody Equipment equipment) {
        Equipment saved = equipmentService.addEquipmentToBuilding(buildingId, equipment);
        return saved != null ? ResponseEntity.ok(saved) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{equipmentId}/associate-meter/{meterId}")
    public ResponseEntity<Equipment> associateEquipmentWithMeter(@PathVariable String equipmentId, @PathVariable String meterId) {
        try {
            Equipment updated = equipmentService.associateEquipmentWithMeter(equipmentId, Long.parseLong(meterId));
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/by-meter/{meterId}")
    public List<Equipment> getEquipmentByMeterId(@PathVariable String meterId) {
        return equipmentService.getEquipmentByMeterId(meterId);
    }

    @GetMapping("/{equipmentId}/validate-association/{meterId}")
    public ResponseEntity<Boolean> validateEquipmentMeterAssociation(@PathVariable String equipmentId, @PathVariable String meterId) {
        boolean isValid = equipmentService.validateEquipmentMeterAssociation(equipmentId, meterId);
        return ResponseEntity.ok(isValid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable String id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
