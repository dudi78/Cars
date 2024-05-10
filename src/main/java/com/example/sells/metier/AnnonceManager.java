package com.example.sells.metier;

import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Car;
import com.example.sells.dao.entities.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface AnnonceManager {
     Annonce AddAnnonce(Annonce a);

     Annonce findannoncebyid(Integer id);
     List<Annonce> findCustomerAdsByUsername(String username);
    String saveImage(MultipartFile image2) throws IOException;
}
