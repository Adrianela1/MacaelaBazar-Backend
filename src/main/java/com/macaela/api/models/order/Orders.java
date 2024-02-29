package com.macaela.api.models.order;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.macaela.api.models.pay.Pay;
import com.macaela.api.models.placedelivery.PlaceDelivery;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Orders")
@Entity(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "order_status")
    private boolean order_status;

    @Column(name = "amount")
    private BigDecimal amount;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Users_User_id")
    @JsonIgnore
    private User userId;

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Place_delivery_place_delivery_id")
    @JsonIgnore
    private PlaceDelivery placeDeliveryId;

    public void setPlaceDelivery(PlaceDelivery placeDelivery) {
        this.placeDeliveryId = placeDelivery;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_pay_id")
    @JsonIgnore
    private Pay payId;

    public void setPay(Pay pay) {
        this.payId = pay;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_Product_id")
    @JsonIgnore
    private Product productId;

    public void setProduct(Product product) {
        this.productId = product;
    }

}
