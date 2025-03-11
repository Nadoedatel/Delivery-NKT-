package com.deliveryfood.pet.models;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

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

    private String roles;

    private String name;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;
    private String card;
    private int reg_data;
    private int history_order;

    @PrePersist
    public void prePersist() {
        if (this.roles == null) {
            this.roles = "ROLE_USER";
        }
    }
}
