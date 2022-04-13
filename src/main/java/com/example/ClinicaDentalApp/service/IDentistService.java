package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.dto.DentistDTO;
import com.example.ClinicaDentalApp.entities.Dentist;

public interface IDentistService extends ICRUDService<DentistDTO>{
    public DentistDTO save(Dentist dentist);
    public Dentist findEntityById(Integer id);

}
