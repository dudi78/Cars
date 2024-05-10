package com.example.sells.metier;

import com.example.sells.dao.entities.Car;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CarManage {

    public Car AddCar(Car c);
    public boolean  deleteCarAndAssociatedAnnonce(Integer id) ;
    public  Car searchcar(Integer id);
}
