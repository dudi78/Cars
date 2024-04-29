package com.example.sells.metier;

import com.example.sells.dao.Repositories.CustomerRepository;
import com.example.sells.dao.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Customerimp implements CustomerManager {
   @Autowired
    CustomerRepository cr;

    @Override
    public Customer AddCustomer(Customer c) {
        return cr.save(c);
    }


    @Override
    public Customer  findCustomerbynomUtilisateur(String  nomUtilisateur) {
        return  cr.findCustomerBynomUtilisateur(nomUtilisateur);
    }


}
