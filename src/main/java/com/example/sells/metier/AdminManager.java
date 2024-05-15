package com.example.sells.metier;

import com.example.sells.dao.entities.Admin;

public interface AdminManager {
    public Admin FindAdminByUserName(String username);
    public Admin AddAdmin(Admin admin);

}
