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
import java.util.UUID;

@Service
public class Annonceimp  implements AnnonceManager{
@Autowired
AnnonceRepository ar;
private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    CarRepository car;

    @Override
    public Annonce AddAnnonce(Annonce a) {
        return ar.save(a);
    }

    @Override
    public String saveImage(MultipartFile image2) throws IOException{
        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + image2.getOriginalFilename();

        // Get the path to save the file
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file to the upload directory
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(image2.getInputStream(), filePath);

        // Return the path to the saved file
        return filePath.toString();
    }


}
