package com.example.demo.controllers.rest;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.entities.dto.CreateOrderDTO;
import com.example.demo.entities.dto.OrderItemsDTO;
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
@RequestMapping("orders")
public class OrderController {
    //order-service-new
    //customer-service-new
    private static final String ORDER_URL = "http://localhost:8084";
    private static final String CUSTOMERS_URL = "http://localhost:8083";
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    @Autowired
    public OrderController() {

    }

    @GetMapping
    public ResponseEntity<List<Order>> show(){
        ResponseEntity<Order[]> response = restTemplate
                .exchange(ORDER_URL + "/orders", HttpMethod.GET, headersEntity, Order[].class);
        List<Order> orders = Arrays.asList(Objects.requireNonNull(response.getBody()));
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orderItems")
    public @ResponseBody
    OrderItemsDTO showThings() {
        ResponseEntity<OrderItemsDTO> response = restTemplate
                .exchange(ORDER_URL + "/orders/orderItems",
                        HttpMethod.GET, headersEntity, OrderItemsDTO.class);
        return response.getBody();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> showById(@PathVariable UUID id) {
        ResponseEntity<Order> response = restTemplate
                .exchange(ORDER_URL + "/orders/" + id, HttpMethod.GET, headersEntity, Order.class);
        Order order = Objects.requireNonNull(response.getBody());
        return ResponseEntity.ok(order);
    }


    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDTO createOrder) {
        createOrder.getCustomer().setCustomerId(UUID.randomUUID());
        //System.out.println(createOrder.getCustomer());

        HttpEntity<CreateOrderDTO> createOrderDTOHttpEntity = new HttpEntity<>(createOrder);
        ResponseEntity<Void> response = restTemplate
                .exchange(ORDER_URL + "/orders",
                        HttpMethod.POST, createOrderDTOHttpEntity, Void.class);

        if (response.getStatusCodeValue() == 200) {
            saveCustomer(createOrder.getCustomer());
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.badRequest().build();
    }

    private void saveCustomer(Customer customer) {
        HttpEntity<Customer> customerHttpEntity = new HttpEntity<>(customer);
        ResponseEntity<Void> response = restTemplate
                .exchange(CUSTOMERS_URL + "/customers",
                        HttpMethod.POST, customerHttpEntity, Void.class);
    }


}
