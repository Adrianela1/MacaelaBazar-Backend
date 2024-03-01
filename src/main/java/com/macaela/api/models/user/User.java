package com.macaela.api.models.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.macaela.api.models.order.Orders;
import com.macaela.api.models.product.Product;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @EqualsAndHashCode(of = "id")
public class User implements UserDetails {

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
	private String banner;
	@Column(name = "customer_bankaccount")
	private int bankaccount;
	@Column(name = "customer_description_page")
	private String descriptionPage;
	@Column(name = "customer_name_page")
	private String namePage;

		
	public User() {
		super();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	@OneToMany(mappedBy = "userId")
	@JsonManagedReference
	private Set<Orders> orders = new HashSet<>();

	public User(DatosRegistroUsuario datosRegistroUsuario) {
	    this.fullname = datosRegistroUsuario.getName();
	    this.email = datosRegistroUsuario.getEmail();
	    this.password = datosRegistroUsuario.getPassword();
	    this.age = datosRegistroUsuario.getAge();
	    this.administrator = datosRegistroUsuario.isAdministrator();
	}

	public User() {
		
	}

	public Long getId() {
		return this.id;
	}

	public void actualizarPagina(DatosRegistroPagina datosRegistroPagina) {
		if (datosRegistroPagina.banner() != null) {
			this.banner = datosRegistroPagina.banner();
		}
		if (datosRegistroPagina.descriptionPage() != null) {
			this.descriptionPage = datosRegistroPagina.descriptionPage();
		}
		if (datosRegistroPagina.namePage() != null) {
			this.namePage = datosRegistroPagina.namePage();
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}



}
