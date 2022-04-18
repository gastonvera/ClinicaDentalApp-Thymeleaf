package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.entities.Appointment;

import java.util.List;

public interface IAppointmentService extends ICRUDService<AppointmentDTO>{
    public List<AppointmentDTO> findAppointmentsForPatientById(Integer patient_id);
    public List<AppointmentDTO> findAppointmentsForDentistById(Integer patient_id);
}
