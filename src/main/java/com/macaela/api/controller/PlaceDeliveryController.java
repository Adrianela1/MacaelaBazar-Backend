package com.macaela.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.macaela.api.models.placedelivery.DatosPlaceDelivery;
import com.macaela.api.models.placedelivery.PlaceDelivery;
import com.macaela.api.models.placedelivery.PlaceDeliveryRepository;

@RestController
@RequestMapping("/placedelivery")
public class PlaceDeliveryController {

	@Autowired
	private PlaceDeliveryRepository placeDeliveryRepository;
	
	@PostMapping
	public void PlaceDelivery(@RequestBody DatosPlaceDelivery datosPlaceDelivery) {
		placeDeliveryRepository.save(new PlaceDelivery(datosPlaceDelivery));
		System.out.println(datosPlaceDelivery);
	}
}



