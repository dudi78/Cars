package com.example.sells.Web;

import com.example.sells.metier.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PagesController {
  // @Autowired
   // private CustomerManager cm;


    @GetMapping("/home")
    public String homepage() {
        return "HomePage";
    }




    @GetMapping("/About")
    public String about(){
        return"About";
    }




}
