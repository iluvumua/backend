package com.example.applicationtelecom.repository;

import com.example.applicationtelecom.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
