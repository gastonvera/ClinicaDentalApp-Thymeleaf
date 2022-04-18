package com.example.ClinicaDentalApp.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private int number;
    private String location;
    private String province;

}