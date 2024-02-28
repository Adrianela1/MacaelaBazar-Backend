	package com.macaela.api.models.user;

public record DatosRegistroUsuario(String name, String email, Integer age,
		String password, String password2, boolean administrator) {

}
