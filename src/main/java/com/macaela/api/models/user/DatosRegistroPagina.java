package com.macaela.api.models.user;


import jakarta.validation.constraints.NotNull;

public record DatosRegistroPagina(@NotNull Long id ,String banner, String descriptionPage, String namePage) {
	
}