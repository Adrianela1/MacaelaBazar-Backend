package com.macaela.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.macaela.api.models.user.User;

@Service
public class TokenService {
	
	@Value("${api.security.secret}")
	private String apiSecret;
	
	public String generarToken(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
		    return JWT.create()
		        .withIssuer("macaela")
		        .withSubject(user.getEmail())
		        .withClaim("is_administrator", user.isAdministrator())
		        .withClaim("id", user.getId())
		        .withExpiresAt(generarFechaExpiracion())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException();
		}
	}
	
	private Instant generarFechaExpiracion() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
	}
	
	
}
