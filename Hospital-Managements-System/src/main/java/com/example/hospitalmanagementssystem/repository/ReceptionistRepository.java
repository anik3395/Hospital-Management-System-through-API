package com.example.hospitalmanagementssystem.repository;

import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.model.Receptionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Long> {
    Optional<Receptionist> findByNameAndPassword(String name, String password);
    // Find doctors by specialization
//    List<Doctor> findBySpecialization(String specialization);
}
