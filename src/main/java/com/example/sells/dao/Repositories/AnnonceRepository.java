package com.example.sells.dao.Repositories;



import com.example.sells.dao.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce,Integer> {
    //@Query("SELECT a FROM Annonce a LEFT JOIN FETCH a.car WHERE a.customer.id = :customerId")
    //List<Annonce>findByCustomerIdWithCar(@Param("customerId") Integer customerId);
}
