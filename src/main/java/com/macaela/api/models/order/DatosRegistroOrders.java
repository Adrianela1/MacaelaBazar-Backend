package com.macaela.api.models.order;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class DatosRegistroOrders {
    private boolean orderStatus;
    private BigDecimal amount;
    private Long userId;
    private Long placeDeliveryId;
    private Long payId;
    private Long productId;

    public boolean isOrderStatus() {
        return this.orderStatus;
    }

    public boolean getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlaceDeliveryId() {
        return this.placeDeliveryId;
    }

    public void setPlaceDeliveryId(Long placeDeliveryId) {
        this.placeDeliveryId = placeDeliveryId;
    }

    public Long getPayId() {
        return this.payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Object getPlaceDelivery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlaceDelivery'");
    }
}
