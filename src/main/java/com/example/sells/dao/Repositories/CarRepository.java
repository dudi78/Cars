package com.example.sells.dao.Repositories;

import com.example.sells.dao.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car,Integer> {

    public Car findCarById(Integer id);
}
