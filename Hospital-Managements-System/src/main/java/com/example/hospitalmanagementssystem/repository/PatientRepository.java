package com.example.hospitalmanagementssystem.repository;

import com.example.hospitalmanagementssystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNameAndPassword(String name, String password);
}
