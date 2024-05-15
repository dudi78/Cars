package com.example.sells.Web;

import com.example.sells.metier.CarManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CarController {

    @Autowired
    CarManage cm;

    @GetMapping("/deleteannonce/{carId}")
    public String DeleteAnnonceCar(@PathVariable Integer carId){

        boolean b= cm.deleteCarAndAssociatedAnnonce(carId);
        return "redirect:/customerads";
    }
}
