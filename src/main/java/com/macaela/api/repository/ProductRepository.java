package com.macaela.api.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macaela.api.models.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
		List<Product> findByUserId_Id(Long userId);
	 
	    List<Product> findByCategory(String category);

	    List<Product> findBySize(String size);

	    List<Product> findByPriceLessThan(BigDecimal price);

	    List<Product> findByPriceGreaterThan(BigDecimal price);
}
