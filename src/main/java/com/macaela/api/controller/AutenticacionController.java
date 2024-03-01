package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.infra.security.DatotsJWTToken;
import com.macaela.api.infra.security.TokenService;
import com.macaela.api.models.product.Product;
import com.macaela.api.models.user.DatosAutenticacionUser;
import com.macaela.api.models.user.DatosRegistroUsuario;
import com.macaela.api.models.user.User;
import com.macaela.api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AutenticacionController {
	
	@Autowired
	private AuthenticationManager authenticactionManager;
	
	@Autowired
	private TokenService tokenService;
	


    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Autowired
    private UserRepository userRepository; 

    @PostMapping("/signup") 
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {
        // Verifica si el correo ya está registrado
        if (userRepository.existsByEmail(datosRegistroUsuario.getEmail())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        // Verifica que las contraseñas coincidan
        if (!datosRegistroUsuario.getPassword().equals(datosRegistroUsuario.getPassword2())) {
            return ResponseEntity.badRequest().body("Las contraseñas no coinciden");
        }

        // Crea un nuevo usuario con los datos del DTO
        User user = new User();
        user.setFullname(datosRegistroUsuario.getName());
        user.setEmail(datosRegistroUsuario.getEmail());
        user.setPassword(passwordEncoder.encode(datosRegistroUsuario.getPassword()));
        user.setAge(datosRegistroUsuario.getAge());
        user.setAdministrator(datosRegistroUsuario.isAdministrator());

        // Guarda el usuario en la base de datos
        userRepository.save(user);

        // Crea el token de autenticación
        var JWTtoken = tokenService.generarToken(user);

        // Retorna el token
        return ResponseEntity.ok(JWTtoken);
    }

	
	
	@PostMapping("/login")
	public ResponseEntity autenticarUser(@RequestBody @Valid DatosAutenticacionUser datosAutenticacionUser) {
		Authentication authtoken = new UsernamePasswordAuthenticationToken(datosAutenticacionUser.email(),
				datosAutenticacionUser.password());
		var userAutenticado = authenticactionManager.authenticate(authtoken);
		var JWTtoken = tokenService.generarToken((User) userAutenticado.getPrincipal());
		return ResponseEntity.ok(new DatotsJWTToken(JWTtoken));	
	}
	
}
