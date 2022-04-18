package com.example.ClinicaDentalApp.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private Integer id;
    private String street;
    private String number;
    private String location;
    private String province;
}
