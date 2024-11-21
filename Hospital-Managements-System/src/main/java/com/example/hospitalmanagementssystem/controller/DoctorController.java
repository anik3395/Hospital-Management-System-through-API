
package com.example.hospitalmanagementssystem.controller;

import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.service.DoctorService;
import com.example.hospitalmanagementssystem.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);

        return new ResponseEntity<>("Doctor registered successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}


