package com.example.sells.metier;

import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Car;
import com.example.sells.dao.entities.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface AnnonceManager {
    public Annonce AddAnnonce(Annonce a);


    String saveImage(MultipartFile image2) throws IOException;
}
