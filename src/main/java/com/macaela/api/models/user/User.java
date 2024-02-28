package com.macaela.api.models.user;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.macaela.api.models.product.Product;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id")
	private Long id;

	@Column(name = "customer_full_name")
	private String fullname;
	@Column(name = "customer_email")
	private String email;
	@Column(name = "customer_password")
	private String password;
	@Column(name = "customer_age")
	private int age;
	@Column(name = "is_administrator")
	private boolean administrator;
	@Column(name = "customer_banner")
	private byte banner;
	@Column(name = "customer_bankaccount")
	private int bankaccount;
	@Column(name = "customer_description_page")
	private String descriptionPáge;
	@Column(name = "customer_name_page")
	private String namePage;
  
public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescriptionPage() {
		return descriptionPage;
	}

	public void setDescriptionPage(String descriptionPage) {
		this.descriptionPage = descriptionPage;
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}
	public void setUserId(Long id) {
		this.id = id;
	}

	// Relaciones
	@OneToMany(mappedBy = "userId")
	private Set<Product> products = new HashSet<>();


	public User(DatosRegistroUsuario datosRegistroUsuario) {
		this.fullname = datosRegistroUsuario.name();
		this.email = datosRegistroUsuario.email();
		this.password = datosRegistroUsuario.password();
		this.password = datosRegistroUsuario.password2();
		this.age = datosRegistroUsuario.age();
		this.administrator = datosRegistroUsuario.administrator();

	}

	public Long getId() {
		return this.id;
	}

	
	public User () {
	}
	
	
	public void actualizarPagina(DatosRegistroPagina datosRegistroPagina) {
		if(datosRegistroPagina.banner() != null) {
			this.banner = datosRegistroPagina.banner();
		}
		if(datosRegistroPagina.descriptionPage() != null) {
			this.descriptionPage = datosRegistroPagina.descriptionPage();
		}
		if(datosRegistroPagina.namePage() != null) {
			this.namePage = datosRegistroPagina.namePage();
		}
	}
	


}
