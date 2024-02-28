package com.macaela.api.models.user;

public record DatosAutenticacionUser(String email, String password) {

	public String email() {
		return email;
	}

	public String password() {
		return password;
	}
	
	
		
}
