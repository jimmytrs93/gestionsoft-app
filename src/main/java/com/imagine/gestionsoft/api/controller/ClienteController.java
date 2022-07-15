package com.imagine.gestionsoft.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.gestionsoft.core.dto.ClienteDto;
import com.imagine.gestionsoft.core.service.IClienteService;

@RequestMapping("v1/cliente")
@RestController
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping(path = "{id}")
	public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable Integer id) {
		ClienteDto resp = clienteService.obtenerCliente(id);
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<ClienteDto> crearCliente(@RequestBody ClienteDto cliente) {
		ClienteDto resp = clienteService.crearCliente(cliente);
		return new ResponseEntity<ClienteDto>(resp, HttpStatus.CREATED);
	}

	@GetMapping(path = "/negocio/{id}")
	public ResponseEntity<List<ClienteDto>> obtenerClientesNegocio(@PathVariable Integer id) {
		List<ClienteDto> clientes = clienteService.obtenerClientesNegocio(id);
		return ResponseEntity.ok(clientes);
	}

}
