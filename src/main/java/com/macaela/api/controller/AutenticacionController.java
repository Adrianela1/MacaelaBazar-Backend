package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.DatosAutenticacionUser;
import com.macaela.api.models.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
	
	@Autowired
	private AuthenticationManager authenticactionManager;
	
	
	@PostMapping
	public ResponseEntity autenticarUser(@RequestBody @Valid DatosAutenticacionUser datosAutenticacionUser) {
		Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUser.email(),
				datosAutenticacionUser.password());
		authenticactionManager.authenticate(token);
		return ResponseEntity.ok().build();
	}
	
}
