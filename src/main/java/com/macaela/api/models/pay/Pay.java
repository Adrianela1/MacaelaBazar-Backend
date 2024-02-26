package com.macaela.api.models.pay;

import java.time.LocalDate;
import java.util.Date;

import com.macaela.api.models.user.DatosRegistroUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pay")
@Entity (name = "Pay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pay {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pay_id" )
	private Long id;
	
	@Column(name = "due_date" )
	private String dueDate;
	
	@Column(name = "full_name" )
	private String fullName;
	
	@Column(name = "bank_number" )
	private String bankNumber;
	
	@Column(name = "cvv" )
	private int cvv;
	
	
	public Pay(DatosRegistroPago datosRegistroPago) {
		this.bankNumber = datosRegistroPago.numeroDeLaTarjeta();
		this.fullName = datosRegistroPago.nombreYApellidos();
		this.dueDate = datosRegistroPago.fechaDeVencimiento();
		this.cvv = datosRegistroPago.codigoDeSeguridad();

	}
	
	
}
