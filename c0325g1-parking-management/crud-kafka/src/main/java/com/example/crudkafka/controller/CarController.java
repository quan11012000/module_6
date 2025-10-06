package com.example.crudkafka.controller;

import com.example.crudkafka.entity.Car;
import com.example.crudkafka.service.CarServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        return carService.save(car);
    }
    public List<Car> saveAll(List<Car> cars) {
        return carService.saveAll(cars);
    }
    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        Car existing = carService.findById(id);
        if (existing != null) {
            existing.setLicensePlate(car.getLicensePlate());
            existing.setBrand(car.getBrand());
            existing.setColor(car.getColor());
            return carService.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
