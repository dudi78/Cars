package com.example.sells.Web;

import com.example.sells.dao.entities.Admin;
import com.example.sells.metier.AdminManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
@Autowired
private AdminManager am;

    @GetMapping("/AdminRegister")
    public String getpagedash(){
        return "AdminRegister";
    }

    @GetMapping("/Dashboard")
    public String getpagedash1(){
        return "Dashbord";
    }


    @PostMapping("/admin/register")
    public String registerAdmin(@RequestParam String username,
                                @RequestParam String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        am.AddAdmin(admin);
        return "redirect:/login";
    }
}
