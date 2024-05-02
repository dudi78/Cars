package com.example.sells.metier;

import com.example.sells.dao.Repositories.CarRepository;
import com.example.sells.dao.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Carimp implements CarManage{
    @Autowired
    CarRepository car;
    @Override
    public Car AddCar(Car c) {
        return car.save(c);
    }
}
