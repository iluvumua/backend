package com.example.applicationtelecom.controller;

import com.example.applicationtelecom.entity.Meter;
import com.example.applicationtelecom.service.MeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meters")
@CrossOrigin(origins = "http://localhost:9002")
public class MeterController {

    @Autowired
    private MeterService meterService;

    @GetMapping
    public List<Meter> getAllMeters() {
        return meterService.getAllMeters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meter> getMeterById(@PathVariable Long id) {
        return meterService.getMeterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Meter createMeter(@RequestBody Meter meter) {
        return meterService.saveMeter(meter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meter> updateMeter(@PathVariable Long id, @RequestBody Meter meter) {
        if (!id.equals(meter.getId())) {
            meterService.deleteMeter(id);
        }
        Meter saved = meterService.saveMeter(meter);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/step1")
    public ResponseEntity<Meter> updateStep1(@PathVariable Long id, @RequestBody String data) {
        Meter meter = meterService.updateStep1(id, data);
        return meter != null ? ResponseEntity.ok(meter) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/step2")
    public ResponseEntity<Meter> updateStep2(@PathVariable Long id, @RequestBody String data) {
        Meter meter = meterService.updateStep2(id, data);
        return meter != null ? ResponseEntity.ok(meter) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/step3")
    public ResponseEntity<Meter> updateStep3(@PathVariable Long id, @RequestBody String data) {
        Meter meter = meterService.updateStep3(id, data);
        return meter != null ? ResponseEntity.ok(meter) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeter(@PathVariable Long id) {
        meterService.deleteMeter(id);
        return ResponseEntity.noContent().build();
    }
}
