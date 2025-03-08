package com.deliveryfood.pet.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class MyUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String name;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;
    private String  card;
    private int reg_data;
    private int history_order;

    
}
