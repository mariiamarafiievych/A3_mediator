package com.example.demo.entities.dto;

import com.example.demo.entities.Customer;

import java.util.List;
import java.util.UUID;

public class CustomersDTO {
    private List<Customer> customers;

    public CustomersDTO() {
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
