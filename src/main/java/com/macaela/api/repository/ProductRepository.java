package com.macaela.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macaela.api.models.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
