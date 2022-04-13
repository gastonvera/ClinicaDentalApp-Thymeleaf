package com.example.ClinicaDentalApp.repository;

import com.example.ClinicaDentalApp.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address,Integer> {
}
