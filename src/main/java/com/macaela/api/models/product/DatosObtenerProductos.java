package com.macaela.api.models.product;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class DatosObtenerProductos {
    private Long id;
    private String category;
    private String color;
    private String size;
    private Integer stock;
    private BigDecimal price;
    private String description;
    private String image;
}
