package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.user.DatosRegistroUsuario;
import com.macaela.api.models.user.User;
import com.macaela.api.models.user.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {

	//con esta anotacion tendras problemas en pruebas unitarias
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public void registrarUsuario(@RequestBody DatosRegistroUsuario datosRegistroUsuario) {
		userRepository.save(new User(datosRegistroUsuario));
		System.out.println(datosRegistroUsuario);
	}
}
