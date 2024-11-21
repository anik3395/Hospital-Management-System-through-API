package com.example.hospitalmanagementssystem.repository;

import com.example.hospitalmanagementssystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween
            (Long doctorId, LocalDateTime start, LocalDateTime end);



    List<Appointment> findByDoctorId (Long doctorId);
}
