package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.user.DatosObtenerPagina;
import com.macaela.api.models.user.DatosRegistroPagina;
import com.macaela.api.models.user.DatosRegistroUsuario;
import com.macaela.api.models.user.User;
import com.macaela.api.repository.UserRepository;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	// con esta anotacion tendras problemas en pruebas unitarias
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public void registrarUsuario(@RequestBody DatosRegistroUsuario datosRegistroUsuario) {
		userRepository.save(new User(datosRegistroUsuario));
		System.out.println(datosRegistroUsuario);
			
		}
	
	@PutMapping
	@Transactional
	public void actualizarPagina(@RequestBody @Valid DatosRegistroPagina datosRegistroPagina) {
		User user = userRepository.getReferenceById(datosRegistroPagina.id());
		user.actualizarPagina(datosRegistroPagina);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPagina(@PathVariable("id") Long id) {
        User user = userRepository.findById(id)
                .orElse(null);
        if (user != null) {
            DatosObtenerPagina datosPagina = new DatosObtenerPagina(user.getBanner(), user.getNamePage(), user.getDescriptionPage());
            return ResponseEntity.ok(datosPagina);
        }    else {
            String usuarioNoEcontrado = "Vendedora no encontrada";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usuarioNoEcontrado);
        }
    }
}

