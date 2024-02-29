package com.macaela.api.models.order;

import java.math.BigDecimal;

import com.macaela.api.models.pay.Pay;
import com.macaela.api.models.placedelivery.PlaceDelivery;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.User;

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
    private Long id;

    @Column(name = "order_status")
    private boolean order_status;

    @Column(name = "amount")
    private BigDecimal amount;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_User_id")
    private User userId;

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_delivery_id")
    private PlaceDelivery placeDelivery;

    public void setPlaceDelivery(PlaceDelivery placeDelivery) {
        this.placeDelivery = placeDelivery;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_id")
    private Pay pay;

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

}
