package com.example.crudkafka.service;

import com.example.crudkafka.entity.Car;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarConsumer {

    private final MailService mailService;

    public CarConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @KafkaListener(topics = "car-topic", groupId = "car-group")
    public void consume(List<Car> cars) {
        System.out.println("Nhận batch size = " + cars.size());
        for (Car car : cars) {
            System.out.println("Xe: " + car.getBrand() + " - " + car.getLicensePlate());

            String subject = "Xe mới được thêm vào hệ thống (batch)";
            String body = "Xe mới: " + car.getBrand() + " - Biển số: " + car.getLicensePlate();

            mailService.sendMail("nguyentienquan12a4@gmail.com", subject, body);
        }
    }
}
