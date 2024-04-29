package com.example.sells.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.sells.dao.entities.Customer;
import com.example.sells.dao.Repositories.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerManager cm;

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        Customer customer = cm.findCustomerbynomUtilisateur(nomUtilisateur);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + nomUtilisateur);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getNomUtilisateur())
                .password(customer.getPassword())
                .roles("USER")
                .build();
    }


}
