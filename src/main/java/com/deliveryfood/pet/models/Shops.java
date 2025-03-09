package com.deliveryfood.pet.models;


import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, img, description, delivery_time;

    public Long getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
