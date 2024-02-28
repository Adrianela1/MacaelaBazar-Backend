package com.macaela.api.models.placedelivery;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "place_delivery")
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDelivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "place_delivery_id" )
	private Long id;
	
	@Column(name = "date_delivery" )
	@Temporal(TemporalType.DATE)
	private String fecha;
	
	@Column(name = "schedule_delivery" )
	private String schedule;

	@Column(name = "time_delivery" )
	private String time;
	
	@Column(name = "line_delivery")
	private String line;
	
	@Column(name = "station_delivery" )
	private String station;
	
	//@OneToMany(mappedBy = "place_delivery")
	//private Set <PlaceDelivery> placeDelivery = new HashSet<>();

	public PlaceDelivery(DatosPlaceDelivery datosPlaceDelivery) {
		
		this.fecha = datosPlaceDelivery.fecha();
		this.schedule = datosPlaceDelivery.schedule();
		this.time = datosPlaceDelivery.time();
		this.line = datosPlaceDelivery.line();
		this.station = datosPlaceDelivery.station();
	}

	
}
	
	
	

