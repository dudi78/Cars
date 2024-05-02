package com.example.sells.Web;

import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Car;
import com.example.sells.dao.entities.Customer;
import com.example.sells.metier.AnnonceManager;
import com.example.sells.metier.CarManage;
import com.example.sells.metier.CustomUserDetailsService;
import com.example.sells.metier.CustomerManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.IOException;

@Controller
public class AnnonceController {


    @Autowired
    private CustomerManager cus;

    @Autowired
    private AnnonceManager annonceService;
    @Autowired
    private CarManage cm;

    @GetMapping("/Annonces")
    public String ads(){
        return "annoncesPage";
    }



    @PostMapping("/save-annonce")
    public String saveAnnonce(@RequestParam("model") String model,
                              @RequestParam("price") double price,
                              @RequestParam("year") int year,
                              @RequestParam("color") String color,
                              @RequestParam("mileage") int mileage,
                              @RequestParam("engineType") String engineType,
                              @RequestParam("transmission") String transmission,
                              @RequestParam("fuelType") String fuelType,
                              @RequestParam("description") String description,
                              @RequestParam("image1") MultipartFile image1,
                              @RequestParam("image2") MultipartFile image2,
                              @RequestParam("image3") MultipartFile image3) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {

            return "redirect:/login";
        }


        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {

            return "error-page";
        }


        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Customer customer = cus.findCustomerbynomUtilisateur(username);
        Car car = new Car();
        car.setModel(model);
        car.setPrice(price);
        car.setCar_year(year);
        car.setColor(color);
        car.setMileage(mileage);
        car.setEngineType(engineType);
        car.setTransmission(transmission);
        car.setFuelType(fuelType);
        Car savedCar = cm.AddCar(car);

        Annonce annonce = new Annonce();
        annonce.setCustomer(customer);
        annonce.setCar(savedCar);
        annonce.setDescription(description);
        annonce.setApproved(false);

        try {
            String image1Path = annonceService.saveImage(image1);
            String image2Path = annonceService.saveImage(image2);
            String image3Path = annonceService.saveImage(image3);
            annonce.setImage1(image1Path);
            annonce.setImage2(image2Path);
            annonce.setImage3(image3Path);
        } catch (IOException e) {

            e.printStackTrace();
        }

        annonceService.AddAnnonce(annonce);
        System.out.println("GOOD." + customer.getNomUtilisateur());
        return "redirect:/profile";
    }



}
