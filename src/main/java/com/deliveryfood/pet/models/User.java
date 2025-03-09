package com.deliveryfood.pet.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private int id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String card;
    private int reg_data;
    private int history_order;
}
