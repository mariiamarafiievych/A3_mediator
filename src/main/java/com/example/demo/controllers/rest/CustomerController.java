package com.example.demo.controllers.rest;

import com.example.demo.entities.Customer;
import com.example.demo.entities.dto.CustomersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("customers")
public class CustomerController {
//customer-service-new
    private static final String CUSTOMERS_URL = "http://localhost:8083";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    private final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Autowired
    CustomerController() {

    }

    @GetMapping
    public ResponseEntity<List<Customer>> show() {
        ResponseEntity<Customer[]> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers", HttpMethod.GET, headersEntity, Customer[].class);
        List<Customer> customers = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(customers);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> showById(@PathVariable UUID id) {
        ResponseEntity<Customer> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers/" + id, HttpMethod.GET, headersEntity, Customer.class);
        Customer customer = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody CustomersDTO dto) {

        HttpEntity<CustomersDTO> createCustomer = new HttpEntity<>(dto);
        ResponseEntity<Customer> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers", HttpMethod.POST,
                        createCustomer, Customer.class);

        return ResponseEntity.ok(Objects.requireNonNull(response.getBody()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        HttpEntity<UUID> deleteCustomerById = new HttpEntity<>(id);
        ResponseEntity<Void> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers/" + id, HttpMethod.DELETE,
                        deleteCustomerById, Void.class);
        return ResponseEntity.noContent().build();
    }

}
