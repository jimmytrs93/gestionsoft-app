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

import com.imagine.gestionsoft.core.dto.NegocioDto;
import com.imagine.gestionsoft.core.service.INegocioService;

@RequestMapping("v1/negocio")
@RestController
public class NegocioController {

	@Autowired
	private INegocioService negocioService;

	@GetMapping(path = "{id}")
	public ResponseEntity<NegocioDto> obtenerNegocio(@PathVariable Integer id) {
		NegocioDto resp = negocioService.obtenerNegocio(id);
		return ResponseEntity.ok(resp);
	}

	@GetMapping
	public ResponseEntity<List<NegocioDto>> obtenerNegocios() {
		List<NegocioDto> resp = negocioService.obtenerNegocios();
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<NegocioDto> crearNegocio(@RequestBody NegocioDto negocioDto) {
		NegocioDto resp = negocioService.crearNegocio(negocioDto);
		return new ResponseEntity<NegocioDto>(resp, HttpStatus.CREATED);
	}

}
