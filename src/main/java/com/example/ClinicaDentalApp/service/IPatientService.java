package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.dto.PatientDTO;
import com.example.ClinicaDentalApp.entities.Patient;

import java.util.List;

public interface IPatientService extends ICRUDService<PatientDTO>{
    public PatientDTO save(Patient patient);
    public PatientDTO findByEmail(String email);
    public List<Patient> traerTodos();
    public Patient findEntityById(Integer id);
}
