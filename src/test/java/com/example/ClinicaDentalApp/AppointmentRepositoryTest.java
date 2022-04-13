package com.example.ClinicaDentalApp;

import com.example.ClinicaDentalApp.entities.Appointment;
import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.entities.Patient;
import com.example.ClinicaDentalApp.repository.IAppointmentRepository;
import com.example.ClinicaDentalApp.repository.IDentistRepository;
import com.example.ClinicaDentalApp.repository.IPatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AppointmentRepositoryTest {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;
    private IPatientRepository iPatientRepository;
    private IDentistRepository iDentistRepository;

    /*
    @Test
    public void testAddNew(){
        //Datos del paciente
        Patient patient = iPatientRepository.getById(11);
        //Datos del odontologo
        Dentist dentist = (Dentist) iDentistRepository.getById(12);
        //turno
        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.now());
        appointment.setConsultingRoom(15);
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        Appointment appointmentSaved = iAppointmentRepository.save(appointment);

        Assertions.assertTrue( appointmentSaved!= null);
    }

     */
}
