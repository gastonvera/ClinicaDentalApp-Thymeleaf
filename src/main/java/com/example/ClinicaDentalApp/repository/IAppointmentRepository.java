package com.example.ClinicaDentalApp.repository;

import com.example.ClinicaDentalApp.dto.AppointmentDTO;
import com.example.ClinicaDentalApp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {

    /**Esta es una query para devolver los turnos de un paciente*/
    @Query(value = "SELECT * FROM appointments WHERE fk_patient_appointment = ?1", nativeQuery = true)
    List<Appointment> findAppointmentsForPatientById(Integer id);

    /**Esta es una query para devolver los turnos asignados a un odontologo*/
    @Query(value = "SELECT * FROM appointments WHERE fk_dentist_appointment = ?1", nativeQuery = true)
    List<Appointment> findAppointmentsForDentistById(Integer id);
}
