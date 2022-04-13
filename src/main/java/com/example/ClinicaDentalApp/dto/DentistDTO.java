package com.example.ClinicaDentalApp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DentistDTO {

    private Integer id;
    private String name;
    private String lastname;
    private String medicalLicense;

}
