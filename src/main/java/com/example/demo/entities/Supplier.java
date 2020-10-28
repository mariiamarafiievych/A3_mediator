package com.example.demo.entities;

import java.util.List;
import java.util.UUID;

public class Supplier {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<Item> addedItems;

    public Supplier(){

    }

    public Supplier(String firstName, String lastName, List<Item> addedItems) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addedItems = addedItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddedItems(List<Item> addedItems) {
        this.addedItems = addedItems;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public List<Item> getAddedItems(){
        return addedItems;
    }


    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addedClothing=" + addedItems +
                '}';
    }
}
