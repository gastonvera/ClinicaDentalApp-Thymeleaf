package com.example.ClinicaDentalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    private Integer id;
    private int consultingRoom;
    private LocalDate date;
    private PatientDTO patient;
    private DentistDTO dentist;
    private String hour;
}
