package com.example.sells.metier;

import com.example.sells.dao.Repositories.AnnonceRepository;
import com.example.sells.dao.Repositories.CarRepository;
import com.example.sells.dao.entities.Annonce;

import com.example.sells.dao.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class Annonceimp  implements AnnonceManager{

@Autowired
AnnonceRepository ar;
@Autowired
CarRepository cr;
private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    CarRepository car;

    @Override
    public Annonce AddAnnonce(Annonce a) {
        return ar.save(a);
    }

    @Override
    public Annonce findannoncebyid(Integer id) {
        return ar.findAnnonceById(id);
    }

    @Override
    public List<Annonce> findallannonces() {
        return ar.findAll();
    }


    @Override
    public List<Annonce> findCustomerAdsByUsername(String username) {
        List<Annonce> ads = ar.findByCustomerNomUtilisateur(username);
        if (ads.isEmpty()) {
            System.out.println("No ads found for user: " + username);
        } else {
            System.out.println("Ads found for user: " + username);
        }
        return ads;
    }

    public String saveImage(MultipartFile image2) throws IOException{

        String fileName = UUID.randomUUID().toString() + "_" + image2.getOriginalFilename();


        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image2.getInputStream(), filePath);

        return filePath.toString();
    }


}
