package com.example.sells;

import com.example.sells.dao.Repositories.AnnonceRepository;
import com.example.sells.dao.entities.Annonce;
import com.example.sells.metier.AnnonceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication

public class SellsApplication {
//    @Autowired
//    AnnonceManager an;
    public static void main(String[] args) {
        SpringApplication.run(SellsApplication.class, args);}
//        @Bean
//        public CommandLineRunner start(){
//            return args -> {
//                String username = "dounia_11";
//                List<Annonce> ads = an.findCustomerAdsByUsername(username);
//
//
//                if (ads.isEmpty()) {
//                    System.out.println("No ads found for user: " + username);
//                } else {
//                    System.out.println("Ads found for user: " + username);
//
//                    for (Annonce ad : ads) {
//                        System.out.println(ad);
//                    }
//                }
//            };

    }

//}
