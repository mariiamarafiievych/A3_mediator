package com.example.demo.entities.dto;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Item;

import java.util.List;

public class CreateOrderDTO {

    private Customer customer;
    private List<Item> items;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
