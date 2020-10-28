package com.example.demo.entities;


import java.util.UUID;

public class Item {
    private UUID id;
    private String name;
    private double price;
    private Supplier addedBy;

    public Item() {

    }

    public Item(UUID id, String name, double price, Supplier addedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addedBy = addedBy;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Supplier getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Supplier addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", addedBy=" + addedBy +
                '}';
    }
}
