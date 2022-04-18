package com.example.ClinicaDentalApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "dentists")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String medicalLicense;
    private int dni;
    @OneToMany(mappedBy = "dentist")
    @JsonIgnore
    private Set<Appointment> appointments;

}
