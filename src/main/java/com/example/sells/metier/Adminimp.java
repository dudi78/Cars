package com.example.sells.metier;

import com.example.sells.dao.Repositories.AdminRepository;
import com.example.sells.dao.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Adminimp implements AdminManager {
@Autowired
private AdminRepository ar;
    @Override
    public Admin FindAdminByUserName(String username) {
        return ar.findByUsername(username);

    }

    @Override
    public Admin AddAdmin(Admin admin) {
        return ar.save(admin);
    }
}
