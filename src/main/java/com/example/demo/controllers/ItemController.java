package com.example.demo.controllers;

import com.example.demo.entities.Item;
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
@RequestMapping("items")
public class ItemController {
    private static final String ITEM_URL = "http://supplier-service-new:8082";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);


    @Autowired
    public ItemController() {
    }

    @GetMapping
    public ResponseEntity<List<Item>> show() {
        ResponseEntity<Item[]> response = restTemplate
                .exchange(ITEM_URL + "/items", HttpMethod.GET, headersEntity, Item[].class);
        List<Item> items = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(items);
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> showById(@PathVariable UUID id) {
        ResponseEntity<Item> response = restTemplate
                .exchange(ITEM_URL + "/items/" + id, HttpMethod.GET, headersEntity, Item.class);
        Item item = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(item);
    }

}
