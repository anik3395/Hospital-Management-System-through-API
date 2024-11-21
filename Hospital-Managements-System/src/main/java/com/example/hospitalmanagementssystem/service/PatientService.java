package com.example.hospitalmanagementssystem.service;

import com.example.hospitalmanagementssystem.model.Patient;
import com.example.hospitalmanagementssystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final List<Patient> loggedInPatients = new ArrayList<>(); // To track logged-in patients

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Login method
    public Optional<Patient> login(String name, String password) {
        Optional<Patient> optionalPatient = patientRepository.findByNameAndPassword(name, password);

        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();

            // Add the patient to the logged-in list
            loggedInPatients.add(patient);

            // Return the optional containing the logged-in patient
            return optionalPatient;
        } else {
            // Throw an exception for invalid credentials
            throw new RuntimeException("Invalid credentials! Login failed.");
        }
    }


    public Patient addPatient(String name, int age, String email, String password) {
        // Using the parameterized constructor
        Patient patient = new Patient(name, age, email, password);
        return patientRepository.save(patient);
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    //Logged in list return
    public List<Patient> getLoggedInPatients() {
        return loggedInPatients; // Return the list of logged-in patients
    }
}
