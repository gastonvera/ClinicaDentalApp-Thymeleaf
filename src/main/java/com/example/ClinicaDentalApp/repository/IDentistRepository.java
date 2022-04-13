package com.example.ClinicaDentalApp.repository;

import com.example.ClinicaDentalApp.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository<T> extends JpaRepository< Dentist , Integer> {
}
