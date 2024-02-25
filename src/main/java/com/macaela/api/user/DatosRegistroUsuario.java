package com.macaela.api.user;

public record DatosRegistroUsuario(String name, String email, Integer age,
		String password, String password2, boolean administrator) {

}
