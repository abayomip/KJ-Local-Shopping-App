package com.example.kjlocalshoppingapp;

import java.time.LocalDateTime;

public class Basket {
    private int userId;

    private  int productId;

    private  int quantity;

    private LocalDateTime dateTime;

    public Basket(int userId, int productId, int quantity, LocalDateTime dateTime) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
