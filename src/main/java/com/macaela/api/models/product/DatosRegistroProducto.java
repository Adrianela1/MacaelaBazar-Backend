package com.macaela.api.models.product;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosRegistroProducto {
	private String category;
	private String color;
	private String size;
	private Integer stock;
	private BigDecimal price;
	private String description;
	private String image;
	private Long userId;

}
