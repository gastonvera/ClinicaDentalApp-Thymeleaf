package com.example.ClinicaDentalApp.service;

import com.example.ClinicaDentalApp.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICRUDService<T> {

    public T save(T t);
    public T getById(Integer id) throws ResourceNotFoundException;
    public void delete(Integer id) throws ResourceNotFoundException;
    public T update(T t) throws ResourceNotFoundException;
    public List<T> findAll();

}
