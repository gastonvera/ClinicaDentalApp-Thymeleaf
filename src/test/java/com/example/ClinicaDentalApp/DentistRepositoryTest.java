package com.example.ClinicaDentalApp;

import com.example.ClinicaDentalApp.entities.Dentist;
import com.example.ClinicaDentalApp.repository.IDentistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class DentistRepositoryTest {
    @Autowired
    private IDentistRepository iDentistRepository;
/*
    @Test
    public void testAddNew(){

        Dentist dentist = new Dentist();
        dentist.setName("Gaston");
        dentist.setLastname("Vera");
        dentist.setEmail("vera.gastonn@gmail.com");
        dentist.setDni(1556669);
        dentist.setMedicalLicense("4555dsdsdsd6");

        Dentist dentistSaved = (Dentist) iDentistRepository.save(dentist);

        assertThat(dentistSaved.getId()).isGreaterThan(0);
    }

 */
}
