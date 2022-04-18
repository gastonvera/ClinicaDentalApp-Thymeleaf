package com.example.ClinicaDentalApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private int dni;
    private LocalDate admissionDate;
    @OneToOne
    @JoinColumn(name = "fk_patient_address")
    private Address address;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private Set<Appointment> appointments;

}
