package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macaela.api.models.pay.DatosRegistroPago;
import com.macaela.api.models.pay.Pay;
import com.macaela.api.repository.PayRepository;

@RestController
@RequestMapping("/pay")
public class PayController {

	// con esta anotacion tendras problemas en pruebas unitarias
	@Autowired
	private PayRepository payRepository;

	@PostMapping
	public void registrarPago(@RequestBody DatosRegistroPago datosRegistroPago) {
		payRepository.save(new Pay(datosRegistroPago));
		System.out.println(datosRegistroPago);
	}
}
