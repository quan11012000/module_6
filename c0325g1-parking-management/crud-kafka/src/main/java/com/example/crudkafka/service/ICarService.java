package com.example.crudkafka.service;

import com.example.crudkafka.entity.Car;

import java.util.List;

public interface ICarService {
    List<Car> findAll();
    Car findById(Long id);
    Car save(Car car);
    void delete(Long id);
}