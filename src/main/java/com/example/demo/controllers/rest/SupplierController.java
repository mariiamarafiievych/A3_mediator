package com.example.demo.controllers.rest;

import com.example.demo.entities.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("suppliers")
public class SupplierController {
   //supplier-service-new
    private static final String SUPPLIER_URL = "http://localhost:8082";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    @Autowired
    public SupplierController() {
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> show() {
        ResponseEntity<Supplier[]> response = restTemplate
                    .exchange(SUPPLIER_URL + "/suppliers", HttpMethod.GET, headersEntity, Supplier[].class);

        List<Supplier> suppliers = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("{id}")
    public ResponseEntity<Supplier> showById(@PathVariable UUID id) {
        ResponseEntity<Supplier> response = restTemplate
                .exchange(SUPPLIER_URL + "/suppliers/" + id, HttpMethod.GET, headersEntity, Supplier.class);
        Supplier supplier = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    public ResponseEntity<Void> ItemsFromSeller(@RequestBody String deliverJson) {
        HttpEntity<String>  serveJson = new HttpEntity<>(deliverJson);
        ResponseEntity<Void> response = restTemplate
                .exchange(SUPPLIER_URL + "/suppliers",
                        HttpMethod.POST, serveJson, Void.class);

        if (response.getStatusCodeValue() == 200)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
