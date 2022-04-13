package com.example.ClinicaDentalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {

    private Integer id;
    private String name;
    private String lastname;
    private int dni;

}