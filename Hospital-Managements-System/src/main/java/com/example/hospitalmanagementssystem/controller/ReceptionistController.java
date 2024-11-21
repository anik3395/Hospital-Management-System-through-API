package com.example.hospitalmanagementssystem.controller;

import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.model.Patient;
import com.example.hospitalmanagementssystem.model.Receptionist;
import com.example.hospitalmanagementssystem.service.DoctorService;
import com.example.hospitalmanagementssystem.service.PatientService;
import com.example.hospitalmanagementssystem.service.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receptionists")
public class ReceptionistController {
    private final ReceptionistService receptionistService;
    private final DoctorService doctorService ;
    private final PatientService patientService;

    public ReceptionistController(ReceptionistService receptionistService,DoctorService doctorService,PatientService patientService) {
        this.receptionistService = receptionistService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerReceptionist(@RequestBody Receptionist receptionist) {

        receptionistService.saveReceptionist(receptionist);
        return new ResponseEntity<>("Receptionist registered successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginReceptionist(@RequestParam String name, @RequestParam String password) {

        try {
            receptionistService.login(name, password);
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Invalid name or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<List<Receptionist>> getAllReceptionists() {
        List<Receptionist> receptionists = receptionistService.getAllReceptionists();
        return new ResponseEntity<>(receptionists, HttpStatus.OK);
    }

    @GetMapping("/logged-in")
    public ResponseEntity<List<Receptionist>> getLoggedInReceptionists() {
        List<Receptionist> loggedInReceptionists = receptionistService.getLoggedInReceptionists();
        return new ResponseEntity<>(loggedInReceptionists, HttpStatus.OK);
    }


    // view all doctors list
    @GetMapping("/view-doctors")
    public ResponseEntity<List<Doctor>> viewAllDoctors() {
        List<Doctor> doctors = receptionistService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }



    // view all doctors for loggedin receptionists
    @GetMapping("/doctors")
    public ResponseEntity<?> viewAllDoctors(@RequestParam Long receptionistId) {
        if (receptionistService.isReceptionistLoggedIn(receptionistId)) { // Check if receptionist is logged in
            return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK); // Fetch all doctors
        } else {
            return new ResponseEntity<>("Access failed. Only loggedin receptionists can view all the doctors.", HttpStatus.FORBIDDEN);
        }
    }

    // add a patient for loggedin receptionists
    @PostMapping("/patients/add")
    public ResponseEntity<String> addPatient(@RequestParam Long receptionistId, @RequestBody Patient patient) {

        if (receptionistService.isReceptionistLoggedIn(receptionistId)) { // Check if receptionist is logged in
            patientService.savePatient(patient); // Save patient
            return new ResponseEntity<>("Patient added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Access failed. Only loggedin receptionists can view all the doctors.", HttpStatus.FORBIDDEN);
        }
    }
}
