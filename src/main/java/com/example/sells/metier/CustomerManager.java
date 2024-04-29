package com.example.sells.metier;

import com.example.sells.dao.entities.Annonce;
import com.example.sells.dao.entities.Customer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import java.util.List;

public interface CustomerManager {

    public Customer AddCustomer(Customer c);

public Customer  findCustomerbynomUtilisateur(String nomUtilisateur);

}
