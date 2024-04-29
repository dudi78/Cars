package com.example.sells.dao.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String username;
    String Password;
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<Annonce> listannonces;




   }