package com.deliveryfood.pet.models;

import jakarta.persistence.*;

@Entity
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MyUsers user;


    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public MyUsers getUser() {
        return user;
    }

    public void setUser(MyUsers user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

