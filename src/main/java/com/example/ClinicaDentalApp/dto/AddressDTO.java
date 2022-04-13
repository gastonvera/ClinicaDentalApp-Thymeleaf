package com.example.ClinicaDentalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDTO {

    private Integer id;
    private String street;
    private String number;
    private String location;
    private String province;
}
