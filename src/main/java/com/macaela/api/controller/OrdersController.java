package com.macaela.api.controller;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.order.DatosRegistroOrders;
import com.macaela.api.models.order.Orders;
import com.macaela.api.models.pay.Pay;
import com.macaela.api.models.placedelivery.PlaceDelivery;
import com.macaela.api.models.placedelivery.PlaceDeliveryRepository;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.User;
import com.macaela.api.repository.OrdersRepository;
import com.macaela.api.repository.PayRepository;
import com.macaela.api.repository.ProductRepository;
import com.macaela.api.repository.UserRepository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final PlaceDeliveryRepository placeDeliveryRepository;
    private final PayRepository payRepository;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    @Autowired
    public OrdersController(OrdersRepository ordersRepository, UserRepository userRepository,
            PlaceDeliveryRepository placeDeliveryRepository, PayRepository payRepository,
            ProductRepository productRepository, EntityManager entityManager) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.placeDeliveryRepository = placeDeliveryRepository;
        this.payRepository = payRepository;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody DatosRegistroOrders ordersDTO) {
        try {
            Orders order = new Orders();
            order.setOrder_status(ordersDTO.isOrderStatus());
            order.setAmount(ordersDTO.getAmount());

            User user = userRepository.findById(ordersDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            order.setUserId(user);

            PlaceDelivery placeDelivery = placeDeliveryRepository.findById(ordersDTO.getPlaceDeliveryId())
                    .orElseThrow(() -> new IllegalArgumentException("Place delivery not found"));
            order.setPlaceDeliveryId(placeDelivery);

            Pay pay = payRepository.findById(ordersDTO.getPayId())
                    .orElseThrow(() -> new IllegalArgumentException("Pay not found"));
            order.setPayId(pay);

            Product product = productRepository.findById(ordersDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            order.setProductId(product);

            Orders newOrder = ordersRepository.save(order);

            return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
     }
    
    /////
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id) {
        try {
            Orders order = entityManager.find(Orders.class, id);

            if (order == null) {
                throw new IllegalArgumentException("Order not found");
            }

            // Cargar manualmente las relaciones asociadas
            Hibernate.initialize(order.getUserId());
            Hibernate.initialize(order.getPlaceDeliveryId());
            Hibernate.initialize(order.getPayId());
            Hibernate.initialize(order.getProductId());

            return ResponseEntity.status(HttpStatus.OK).body(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        try {
            // Verificar si la orden existe
            Orders order = ordersRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));

            // Eliminar la orden
            ordersRepository.delete(order);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}