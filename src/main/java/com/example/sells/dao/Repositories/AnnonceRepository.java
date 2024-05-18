package com.example.sells.dao.Repositories;



import com.example.sells.dao.entities.Annonce;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce,Integer> {
    List<Annonce> findByCustomerNomUtilisateur(String nomUtilisateur);
    List<Annonce> findAll();
    boolean removeAnnonceById(Integer id);
    Annonce findAnnonceById(Integer id);
    @Query("SELECT a FROM Annonce a WHERE " +
            "(:price IS NULL OR a.car.price = :price) AND " +
            "(:modelParam IS NULL OR a.car.model LIKE %:modelParam%) AND " +
            "(:carYear IS NULL OR a.car.car_year = :carYear) AND " +
            "(:color IS NULL OR a.car.color LIKE %:color%) AND " +
            "(:mileage IS NULL OR a.car.mileage = :mileage) AND " +
            "(:engineType IS NULL OR a.car.engineType = :engineType) AND " +
            "(:transmission IS NULL OR a.car.transmission = :transmission) AND " +
            "(:fuelType IS NULL OR a.car.fuelType = :fuelType)")
    Page<Annonce> findByCriteria(Pageable pageable, Integer price, String modelParam, Integer carYear, String color, Integer mileage, String engineType, String transmission, String fuelType);
}
