package com.imagine.gestionsoft.api.security;

import static com.imagine.gestionsoft.core.constans.GestionConstantesRespuesta.COD_ERR_AUTH;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.imagine.gestionsoft.core.exception.GestionCampoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);

		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		return token;
	}

	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new GestionCampoException(COD_ERR_AUTH, "Firma JWT no valida");
		} catch (MalformedJwtException ex) {
			throw new GestionCampoException(COD_ERR_AUTH, "Token JWT no valida");
		} catch (ExpiredJwtException ex) {
			throw new GestionCampoException(COD_ERR_AUTH, "Token JWT caducado");
		} catch (UnsupportedJwtException ex) {
			throw new GestionCampoException(COD_ERR_AUTH, "Token JWT no compatible");
		} catch (IllegalArgumentException ex) {
			throw new GestionCampoException(COD_ERR_AUTH, "La cadena claims JWT esta vacia");
		}
	}

}
