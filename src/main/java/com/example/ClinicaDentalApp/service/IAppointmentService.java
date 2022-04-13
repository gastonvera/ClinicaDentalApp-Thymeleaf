package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.entities.Appointment;

import java.util.List;

public interface IAppointmentService extends ICRUDService<AppointmentDTO>{
    public AppointmentDTO save(Appointment appointment);
    public List<Appointment> findAllEntity();
    public Appointment findEntityById(Integer id);
    public List<Appointment> findAppointmentsById(Integer patient_id);
}
