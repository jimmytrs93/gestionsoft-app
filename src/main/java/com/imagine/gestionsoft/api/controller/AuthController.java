package com.imagine.gestionsoft.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.gestionsoft.api.dto.JwtDto;
import com.imagine.gestionsoft.api.dto.LoginDto;
import com.imagine.gestionsoft.api.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping()
	public ResponseEntity<JwtDto> authenticateUser(@RequestBody LoginDto loginDTO) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generarToken(authentication);

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String[] roles = userDetails.getAuthorities().stream().map(rol -> rol.getAuthority()).toArray(String[]::new);
		JwtDto jwtDto = new JwtDto(token, userDetails.getUsername(), roles);

		return ResponseEntity.ok(jwtDto);
	}

}
