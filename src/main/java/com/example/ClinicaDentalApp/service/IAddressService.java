package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.dto.AddressDTO;
import com.example.ClinicaDentalApp.entities.Address;

public interface IAddressService extends ICRUDService<AddressDTO>{
    public AddressDTO save(Address address);
}
