package com.example.sells.dao.Repositories;



import com.example.sells.dao.entities.Annonce;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce,Integer> {
    List<Annonce> findByCustomerNomUtilisateur(String nomUtilisateur);
    boolean removeAnnonceById(Integer id);
    Annonce findAnnonceById(Integer id);
}
