package com.macaela.api.models.product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.macaela.api.models.order.Orders;
import com.macaela.api.models.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product")
@Entity(name = "Product")
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
    private Integer stock;

    @Column(name = "Productc_price")
    private BigDecimal price;

    @Column(name = "Product_description")
    private String description;

    @Column(name = "Product_image")
    private String image;
    

    public Product() {
		super();
	}

	public Product(Long id, String category, String color, String size, Integer stock, BigDecimal price,
			String description, String image, User userId, Set<Orders> orders) {
		super();
		this.id = id;
		this.category = category;
		this.color = color;
		this.size = size;
		this.stock = stock;
		this.price = price;
		this.description = description;
		this.image = image;
		this.userId = userId;
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	// Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_User_id")
    private User userId;

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @OneToMany(mappedBy = "productId")
    @JsonManagedReference
    private Set<Orders> orders = new HashSet<>();

	public User getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}
