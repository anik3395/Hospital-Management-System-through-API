package com.example.hospitalmanagementssystem.service;

import com.example.hospitalmanagementssystem.model.Appointment;
import com.example.hospitalmanagementssystem.model.Doctor;
import com.example.hospitalmanagementssystem.model.DoctorStatus;
import com.example.hospitalmanagementssystem.model.Patient;
import com.example.hospitalmanagementssystem.repository.AppointmentRepository;
import com.example.hospitalmanagementssystem.repository.DoctorRepository;
import com.example.hospitalmanagementssystem.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime dateTime){
        Doctor doctor = doctorRepository.findById(doctorId).orElse(  null);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }

        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }

        if (!doctor.getStatus().equals(DoctorStatus.AVAILABLE)) {
            throw new RuntimeException("Doctor is not available for appointments.");
        }

        // Check for overlapping appointments
        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
                doctorId,
                dateTime.minusMinutes(30),  // gap time 30 mins
                dateTime.plusMinutes(30)
        );

        if (!existingAppointments.isEmpty()) {
            throw new RuntimeException("This time slot is already booked for the doctor.");
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDateTime(dateTime);
        appointment.setStatus(DoctorStatus.ENGAGED);

        return appointmentRepository.save(appointment);

    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        System.out.println("######################");

//        List<Appointment> test= appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(
//                doctorId, LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        
        List<Appointment> test = appointmentRepository.findByDoctorId(doctorId);
        System.out.println(test);//Batch Query and Custom Query
        return test;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
