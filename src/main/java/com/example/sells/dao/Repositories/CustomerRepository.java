package com.example.sells.dao.Repositories;

import com.example.sells.dao.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


   Customer  findCustomerBynomUtilisateur(String  nomUtilisateur);
}
