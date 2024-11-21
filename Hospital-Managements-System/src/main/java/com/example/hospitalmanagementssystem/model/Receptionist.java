package com.example.hospitalmanagementssystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receptionists")
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String password;

    // Default constructor
    public Receptionist() {
    }

    // Parameterized constructor
    public Receptionist(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
