package com.example.demo.entities;

import java.util.UUID;


public class Order {
    private UUID id;
    private UUID customerId;

    public Order() {
    }

    public Order(UUID id, UUID customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
