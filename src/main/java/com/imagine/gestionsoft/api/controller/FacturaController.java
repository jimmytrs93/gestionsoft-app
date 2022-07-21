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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.gestionsoft.core.dto.FacturaDto;
import com.imagine.gestionsoft.core.service.IFacturaService;

@RequestMapping("v1/factura")
@RestController
public class FacturaController {

	@Autowired
	private IFacturaService facturaService;

	@GetMapping
	public ResponseEntity<FacturaDto> obtenerFactura(@RequestParam Integer factura, @RequestParam Integer negocio) {
		FacturaDto resp = facturaService.consultarFactura(factura, negocio);
		return ResponseEntity.ok(resp);
	}

	@GetMapping(path = "/negocio/{id}")
	public ResponseEntity<List<FacturaDto>> obtenerFacturasNegocio(@PathVariable Integer id) {
		List<FacturaDto> resp = facturaService.consultarFacturasNegocio(id);
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<FacturaDto> crearFactura(@RequestBody FacturaDto facturaDto) {
		FacturaDto dto = facturaService.crearFactura(facturaDto);
		return new ResponseEntity<FacturaDto>(dto, HttpStatus.CREATED);
	}

}
