package com.example.hospitalmanagementssystem.service;

import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void saveDoctor(Doctor doctor) {

        System.out.println(doctor.getName());


        doctorRepository.save(doctor);


    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}

