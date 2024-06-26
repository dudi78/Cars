package com.example.sells.Web;

import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Car;
import com.example.sells.dao.entities.Customer;
import com.example.sells.metier.AnnonceManager;
import com.example.sells.metier.CarManage;
import com.example.sells.metier.CustomUserDetailsService;
import com.example.sells.metier.CustomerManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.IOException;
import java.util.List;

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
    @GetMapping("/home")
    public String home(){
        return "HomePage";
    }


    @GetMapping("/CarDetails/{Id}")
    public String cardetails(@PathVariable Integer Id, Model model) {
        Annonce annonce = annonceService.findannoncebyid(Id);
        model.addAttribute("annonce", annonce);
        return "Cardetails";
    }



    @GetMapping("/customerads")
    public String customerAds(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {

            return "redirect:/login";
        }


        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserDetails)) {

            return "error-page";
        }


        UserDetails userDetails = (UserDetails) principal;


        String username = userDetails.getUsername();


        List<Annonce> customerAds = annonceService.findCustomerAdsByUsername(username);


        model.addAttribute("customerAds", customerAds);


        return "UpdateCar";
    }



    @PostMapping("/save-annonce")
    public void saveAnnonce(@RequestParam(name = "id", required = false) Integer id,
                              @RequestParam("model") String model,
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


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Customer customer = cus.findCustomerbynomUtilisateur(username);


        if (id != null) {

            Annonce existingAnnonce = annonceService.findannoncebyid(id);
            existingAnnonce.setDescription(description);
            existingAnnonce.setApproved(false);
            if(image1!=null && image2!=null && image3!=null) {
                try {
                    String image1Path = annonceService.saveImage(image1);
                    String image2Path = annonceService.saveImage(image2);
                    String image3Path = annonceService.saveImage(image3);
                    existingAnnonce.setImage1(image1Path);
                    existingAnnonce.setImage2(image2Path);
                    existingAnnonce.setImage3(image3Path);
                } catch (IOException e) {
                    e.printStackTrace();


                }
            }
            Annonce ann=annonceService.AddAnnonce(existingAnnonce);
            Car car =  ann.getCar();
            car.setModel(model);
            car.setPrice(price);
            car.setCar_year(year);
            car.setColor(color);
            car.setMileage(mileage);
            car.setEngineType(engineType);
            car.setTransmission(transmission);
            car.setFuelType(fuelType);
            car.setAnnonce(existingAnnonce);
            car.setState(false);
            car.setRejectionCase(false);
            cm.AddCar(car);



        } else {

            Annonce annonce = new Annonce();
            annonce.setCustomer(customer);
            annonce.setDescription(description);
            annonce.setApproved(false);
            if(image1!=null &&image2!=null&&image3!=null){
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
            }

            Annonce savedannonce=annonceService.AddAnnonce(annonce);
            Car car = new Car();
            car.setModel(model);
            car.setPrice(price);
            car.setCar_year(year);
            car.setColor(color);
            car.setMileage(mileage);
            car.setEngineType(engineType);
            car.setTransmission(transmission);
            car.setFuelType(fuelType);
            car.setAnnonce(savedannonce);
            car.setState(false);
            car.setRejectionCase(false);
            cm.AddCar(car);


        }

        System.out.println("GOOD." + customer.getNomUtilisateur());

    }

@GetMapping("/Updateannonce/{Id}")
    public String pageupdate(Model model,@PathVariable Integer Id) {
    Annonce annonce = annonceService.findannoncebyid(Id);
    Car car = annonce.getCar();


    model.addAttribute("annonce", annonce);
    model.addAttribute("car", car);

    return "annoncedisplay";
}


@GetMapping("/ads")
    public String annoncespage(Model model){

        List<Annonce> Annonces=annonceService.findallannonces();
        model.addAttribute("annonces",Annonces);
        return "annoncesPage";
}

    @GetMapping("/getlistAnnnonces")
    public String annoncespage2( Model model , @RequestParam(name = "page", defaultValue = "0" ) int page,
                                 @RequestParam(name = "taille", defaultValue = "5" ) int taille) {


        Page<Annonce> AllAnnonces= annonceService.getAllAnnonces(page,taille);
        model.addAttribute("listAnnnonces",AllAnnonces.getContent());
        int[] pages = new int[AllAnnonces.getTotalPages()];
        model.addAttribute("pages" , pages);
        model.addAttribute("currentpage", page);
        return "Approveads";
    }

    @GetMapping("/ApproveAnnonce/{Id}")
    public String appdet(@PathVariable Integer Id, Model model) {
        Annonce annonce = annonceService.findannoncebyid(Id);
        model.addAttribute("annonce", annonce);
        return "CarApprove";
    }




@PostMapping ("/setapprove")
    public String setapprove(@RequestParam("id") Integer id ){

       Annonce an=  annonceService.findannoncebyid(id);
       an.setApproved(true);
       an.getCar().setState(true);
       annonceService.AddAnnonce(an);
        return"redirect:getlistAnnnonces";

}
    @PostMapping ("/setstate")
    public String setstate(@RequestParam("id") Integer id ){

        Annonce an=  annonceService.findannoncebyid(id);

        an.getCar().setState(false);
        an.getCar().setRejectionCase(true);
        annonceService.AddAnnonce(an);
        return"redirect:getlistAnnnonces";

    }



    @GetMapping("/adsadmin")
    public String annoncespage(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "5") int taille,
                               @RequestParam(name = "price", required = false) Integer price,
                               @RequestParam(name = "model", required = false) String modelParam,
                               @RequestParam(name = "car_year", required = false) Integer carYear,
                               @RequestParam(name = "color", required = false) String color,
                               @RequestParam(name = "mileage", required = false) Integer mileage,
                               @RequestParam(name = "engineType", required = false) String engineType,
                               @RequestParam(name = "transmission", required = false) String transmission,
                               @RequestParam(name = "fuelType", required = false) String fuelType) {

        Page<Annonce> allAnnonces;

        if (price != null || modelParam != null || carYear != null || color != null || mileage != null || engineType != null || transmission != null || fuelType != null) {
            allAnnonces = annonceService.searchAnnonces(page, taille, price, modelParam, carYear, color, mileage, engineType, transmission, fuelType);
        } else {
            allAnnonces = annonceService.getAllAnnonces(page, taille);
        }

        model.addAttribute("annonces", allAnnonces.getContent());
        int[] pages = new int[allAnnonces.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("currentpage", page);
        return "CriteriaAnnonces";
    }

    }






