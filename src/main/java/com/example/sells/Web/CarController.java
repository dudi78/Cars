package com.example.sells.Web;

import com.example.sells.dao.Repositories.CarRepository;
import com.example.sells.dao.entities.Car;
import com.example.sells.metier.CarManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    @Autowired
    CarManage cm;

    @GetMapping("/deleteannonce/{carId}")
    public String DeleteAnnonceCar(@PathVariable Integer carId){

        boolean b= cm.deleteCarAndAssociatedAnnonce(carId);
        return "UpdateCar";
    }
}
