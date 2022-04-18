package com.example.ClinicaDentalApp.dto;

import lombok.*;

@Data
public class DentistDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String medicalLicense;
    private int dni;

}
