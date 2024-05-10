package com.example.sells.dao.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Annonce")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Admin admin;

    @OneToOne(mappedBy = "annonce", cascade = CascadeType.ALL)
    private Car car;


    private String description;


    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    private Boolean approved;

}