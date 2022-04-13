package com.example.ClinicaDentalApp.repository;

import com.example.ClinicaDentalApp.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer> {

    /**Esta es una query para devolver los turnos de un paciente*/
    @Query(value = "SELECT * FROM appointments WHERE patient_id = ?1", nativeQuery = true)
    List<Appointment> findAppointmentsById(Integer id);
}
