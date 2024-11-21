package com.example.hospitalmanagementssystem.service;

import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.model.Patient;
import com.example.hospitalmanagementssystem.model.Receptionist;
import com.example.hospitalmanagementssystem.repository.DoctorRepository;
import com.example.hospitalmanagementssystem.repository.PatientRepository;
import com.example.hospitalmanagementssystem.repository.ReceptionistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptionistService {

    private final ReceptionistRepository receptionistRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    private final List<Receptionist> loggedInReceptionists = new ArrayList<>();


    public ReceptionistService(ReceptionistRepository receptionistRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository) {
        this.receptionistRepository = receptionistRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    // Save a new receptionist
    public void saveReceptionist(Receptionist receptionist) {
        receptionistRepository.save(receptionist);
    }

    // Get all receptionists
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    // Login a receptionist by login method used: . //which declear in Controller
    public Optional<Receptionist> login(String name, String password) {
        Optional<Receptionist> optionalReceptionist = receptionistRepository.findByNameAndPassword(name, password);

        if (optionalReceptionist.isPresent()) {
            Receptionist receptionist = optionalReceptionist.get();
            loggedInReceptionists.add(receptionist);
            return Optional.of(receptionist);
        } else {
            throw new RuntimeException("Invalid credentials! Login failed.");// assingment
        }
    }

    // Get logged-in receptionists
    public List<Receptionist> getLoggedInReceptionists() {
        return loggedInReceptionists;
    }

    // View all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }



    // New method to check if a receptionist is logged in
    public boolean isReceptionistLoggedIn(Long id) {
        //return loggedInReceptionists.stream().anyMatch(receptionist -> receptionist.getId().equals(id));
        for (Receptionist receptionist : loggedInReceptionists) {
            if (receptionist.getId().equals(id)) {
                return true; // Found the receptionist in the logged-in list
            }
        }
        return false; // Receptionist not found in the logged-in list
    }
}
