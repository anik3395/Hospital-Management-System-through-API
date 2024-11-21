package com.example.hospitalmanagementssystem.controller;

import com.example.hospitalmanagementssystem.model.Patient;
import com.example.hospitalmanagementssystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginPatient(@RequestParam String name, @RequestParam String password) {
        Optional<Patient> patient = patientService.login(name, password);
        if (patient.isPresent()) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid name or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logged-in")
    public ResponseEntity<List<Patient>> getLoggedInPatients() {
        List<Patient> loggedInPatients = patientService.getLoggedInPatients();
        return new ResponseEntity<>(loggedInPatients, HttpStatus.OK);
    }

    

    // Endpoint to register a new patient
    @PostMapping("/register")
    public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}


