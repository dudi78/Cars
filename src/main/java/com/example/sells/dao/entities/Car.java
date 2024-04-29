package com.example.sells.dao.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Car")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double price;
    private String model;
    private int car_year;
    private String color;
    private int mileage;
    private String engineType;
    private String transmission;
    private String fuelType;
    private Boolean State;
    @OneToOne
    private Annonce annonce;


}