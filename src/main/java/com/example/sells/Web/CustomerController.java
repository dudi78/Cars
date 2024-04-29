package com.example.sells.Web;

import ch.qos.logback.core.model.Model;
import com.example.sells.dao.entities.Customer;
import com.example.sells.metier.CustomUserDetailsService;
import com.example.sells.metier.CustomerManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CustomerController {
@Autowired
 private  CustomerManager   cm;
@Autowired private CustomUserDetailsService CustomUserDetailsS;

    @GetMapping("/Register")
    public String registerpage() {
        return "RegisterCustomer";
    }


    @PostMapping("/register")
    public String registerUser(@RequestParam String nomUtilisateur,
                               @RequestParam String phoneNumber,
                               @RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String location,
                               @RequestParam String password
                               ) {

        Customer customer = new Customer();
        customer.setNomUtilisateur(nomUtilisateur);
        customer.setPhoneNumber(phoneNumber);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setLocation(location);
        customer.setPassword(password);


       cm.AddCustomer(customer);
        Customer addedCustomer = cm.AddCustomer(customer);
        if (addedCustomer != null) {
            System.out.println("Customer added successfully: " + addedCustomer);
        } else {
            System.out.println("Failed to add customer. Please try again.");
        }

        return "redirect:login";
    }



    @GetMapping("/profile")
    public String profilpage() {
        return "Profile";
    }

    @GetMapping("/login")
    public String LoginPage(){
        return "loginpage";
    }

    @GetMapping("/myhome")
    public String LoginPage2(){
        return "HomePage";
    }

}
