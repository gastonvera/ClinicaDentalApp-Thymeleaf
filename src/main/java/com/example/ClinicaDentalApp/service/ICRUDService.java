package com.example.ClinicaDentalApp.service;

import java.util.List;

public interface ICRUDService<T> {

    public T save(T t);
    public T getById(Integer id);
    public void delete(Integer id);
    public T update(T t);
    public List<T> findAll();

}
