package com.example.ClinicaDentalApp.dto;

import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.entities.Patient;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {
    private Integer id;
    private Dentist dentist;
    private Patient patient;
    private LocalDate date;
    private String hour;
    private int consultingRoom;
}
