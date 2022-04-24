package com.example.ClinicaDentalApp;

import com.example.ClinicaDentalApp.entities.Address;
import com.example.ClinicaDentalApp.entities.Patient;
import com.example.ClinicaDentalApp.repository.IAddressRepository;
import com.example.ClinicaDentalApp.repository.IPatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PatientRepositoryTest {

    @Autowired
    private IPatientRepository iPatientRepository;

    @Autowired
    private IAddressRepository iAddressRepository;

    @Test
    public void testAddNew(){
        Address address = new Address();
        address.setStreet("Ameghino");
        address.setNumber(1525);
        address.setLocation("Resistencia");
        address.setProvince("Chaco");
        iAddressRepository.save(address);
        Patient patient = new Patient();
        patient.setName("Julian");
        patient.setLastname("Mac Allister");
        patient.setAddress(address);
        patient.setDni(15455);
        patient.setEmail("julian@gmail.com");
        patient.setAdmissionDate(LocalDate.now());

        Patient patientSaved = iPatientRepository.save(patient);

        assertThat(patientSaved.getId()).isGreaterThan(0);
    }
}
