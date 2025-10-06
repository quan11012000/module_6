package com.example.crudkafka.service;

import com.example.crudkafka.entity.Car;
import com.example.crudkafka.repository.ICarRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements ICarService {
    private final KafkaTemplate<String, Car> kafkaTemplate;
    private final ICarRepository carRepository;

    public CarServiceImpl(KafkaTemplate<String, Car> kafkaTemplate, ICarRepository carRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public Car save(Car car) {
        Car saved = carRepository.save(car);
        // Publish event vào Kafka
        kafkaTemplate.send("car-topic", saved);
        return saved;
    }
    public List<Car> saveAll(List<Car> cars) {
        return carRepository.saveAll(cars);
    }
    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }
}