package com.example.hospitalmanagementssystem.controller;

import com.example.hospitalmanagementssystem.model.Appointment;
import com.example.hospitalmanagementssystem.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestParam Long doctorId, @RequestParam Long patientId,
            @RequestParam String dateTime) {

        try {
            LocalDateTime appointmentTime = LocalDateTime.parse(dateTime);
            appointmentService.bookAppointment(doctorId, patientId, appointmentTime);
            return new ResponseEntity<>("Appointment booked successfully", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {

        List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {

        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
