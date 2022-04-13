package com.example.ClinicaDentalApp.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "dentists")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private String email;
    @Column(name = "medical_license")
    private String medicalLicense;
    @Column
    private int dni;
}
