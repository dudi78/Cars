package com.example.sells.dao.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Customer")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String nomUtilisateur;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String location;
    private String password;
    private String role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Annonce> announcements;
}