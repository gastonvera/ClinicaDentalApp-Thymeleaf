package com.example.ClinicaDentalApp.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fk_patient_appointment",referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "fk_dentist_appointment", referencedColumnName = "id")
    private Dentist dentist;
    private LocalDate date;
    private String hour;
    private int consultingRoom;

}
