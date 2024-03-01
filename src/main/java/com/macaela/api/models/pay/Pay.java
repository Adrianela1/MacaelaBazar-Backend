package com.macaela.api.models.pay;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.macaela.api.models.order.Orders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pay")
@Entity(name = "Pay")

public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pay_id")
	private Long id;

	@Column(name = "due_date")
	private String dueDate;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "bank_number")
	private String bankNumber;

	@Column(name = "cvv")
	private int cvv; 
	
	

	public Pay() {
		super();
	}

	public Pay(Long id, String dueDate, String fullName, String bankNumber, int cvv, Set<Orders> orders) {
		super();
		this.id = id;
		this.dueDate = dueDate;
		this.fullName = fullName;
		this.bankNumber = bankNumber;
		this.cvv = cvv;
		this.orders = orders;
	}

	public Pay(DatosRegistroPago datosRegistroPago) {
		this.bankNumber = datosRegistroPago.numeroDeLaTarjeta();
		this.fullName = datosRegistroPago.nombreYApellidos();
		this.dueDate = datosRegistroPago.fechaDeVencimiento();
		this.cvv = datosRegistroPago.codigoDeSeguridad();

	}

	public void setPayId(Long id) {
		this.id = id;
	}

	// Relaciones
	@OneToMany(mappedBy = "order_id")
	@JsonManagedReference
	private Set<Orders> orders = new HashSet<>();
}
