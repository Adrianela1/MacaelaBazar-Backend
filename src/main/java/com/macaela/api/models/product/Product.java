package com.macaela.api.models.product;

import com.macaela.api.models.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")
@Entity(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private Long id;

    @Column(name = "Product_category")
    private String category;

    @Column(name = "Product_color")
    private String color;

    @Column(name = "Product_size")
    private String size;

    @Column(name = "Product_stock")
    private int stock;

    @Column(name = "Product_price")
    private double price;

    @Column(name = "Product_description")
    private String description;

    @Column(name = "Product_image")
    private String image;

    @Column(name = "users_User_id")
    private User userId;
}
