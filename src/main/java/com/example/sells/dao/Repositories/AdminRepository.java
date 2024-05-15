package com.example.sells.dao.Repositories;

import com.example.sells.dao.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
   Admin findByUsername(String username);
}
