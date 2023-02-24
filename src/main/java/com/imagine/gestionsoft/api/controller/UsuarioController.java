package com.imagine.gestionsoft.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.gestionsoft.core.dto.UsuarioDto;
import com.imagine.gestionsoft.core.service.IUsuarioService;

@RequestMapping("v1/usuario")
@RestController
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<UsuarioDto> consultarUsuarioNegocio(@RequestParam Integer usuario,
			@RequestParam Integer negocio) {
		UsuarioDto resp = usuarioService.obtenerUsuarioNegocio(usuario, negocio);
		return ResponseEntity.ok(resp);
	}

	@GetMapping(path = "/negocio/{id}")
	public ResponseEntity<List<UsuarioDto>> consultarUsuariosNegocio(@PathVariable Integer id) {
		List<UsuarioDto> list = usuarioService.obtenerUsuariosNegocio(id);
		return ResponseEntity.ok(list);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
		usuarioDto.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
		UsuarioDto resp = usuarioService.crearUsuario(usuarioDto);
		return new ResponseEntity<UsuarioDto>(resp, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping
	public ResponseEntity<UsuarioDto> editarUsuario(@RequestBody UsuarioDto usuarioDto) {
		UsuarioDto resp = usuarioService.editarUsuario(usuarioDto);
		return new ResponseEntity<UsuarioDto>(resp, HttpStatus.OK);
	}

}
