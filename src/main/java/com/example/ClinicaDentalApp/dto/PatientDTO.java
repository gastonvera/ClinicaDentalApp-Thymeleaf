package com.example.ClinicaDentalApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private int dni;
    private LocalDate admissionDate;

}