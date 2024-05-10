package com.example.sells.metier;

import com.example.sells.dao.Repositories.AnnonceRepository;
import com.example.sells.dao.Repositories.CarRepository;
import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Car;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Carimp implements CarManage {
    @Autowired
    CarRepository carep;
    @Autowired
    AnnonceRepository an;

    @Override
    public Car AddCar(Car c) {
        return carep.save(c);
    }

    @Transactional
    @Override
    public boolean deleteCarAndAssociatedAnnonce(Integer carId) {
        try {

            Car car = carep.findById(carId).orElse(null);
            if (car != null) {

                carep.delete(car);

                if (car.getAnnonce() != null) {

                    an.delete(car.getAnnonce());
                }
                return true;
            }
            return false;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Car searchcar(Integer id) {
        return carep.findCarById(id);
    }
}
