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

import com.imagine.gestionsoft.core.dto.ColaboradorDto;
import com.imagine.gestionsoft.core.service.IColaboradorService;

@RequestMapping("v1/colaborador")
@RestController
public class ColaboradorController {

	@Autowired
	private IColaboradorService colaboradorService;

	@GetMapping(path = "{id}")
	public ResponseEntity<List<ColaboradorDto>> obtenerColaboradoresNegocio(@PathVariable Integer id) {
		List<ColaboradorDto> resp = colaboradorService.obtenerColaboradoresNegocio(id);
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<ColaboradorDto> crearColaborador(@RequestBody ColaboradorDto dto) {
		ColaboradorDto save = colaboradorService.crearColaborador(dto);
		return new ResponseEntity<ColaboradorDto>(save, HttpStatus.CREATED);
	}

}
