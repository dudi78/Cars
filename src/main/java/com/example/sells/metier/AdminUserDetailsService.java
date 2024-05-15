package com.example.sells.metier;



import com.example.sells.dao.Repositories.AdminRepository;
import com.example.sells.dao.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminManager am;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = am.FindAdminByUserName(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}
