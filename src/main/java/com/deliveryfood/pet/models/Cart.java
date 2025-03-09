package com.deliveryfood.pet.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItems> items = new ArrayList<>();

    @ManyToOne
    private MyUsers user;

    // Метод в классе Cart
    public void addItem(Product product, int quantity) {
        CartItems item = new CartItems(this, product, quantity);
        items.add(item);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItems> getItems() {
        return items;
    }

    public void setItems(List<CartItems> items) {
        this.items = items;
    }

    public MyUsers getUser() {
        return user;
    }

    public void setUser(MyUsers user) {
        this.user = user;
    }
}
