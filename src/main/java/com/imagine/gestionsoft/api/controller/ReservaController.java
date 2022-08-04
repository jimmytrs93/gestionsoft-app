package com.imagine.gestionsoft.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imagine.gestionsoft.core.dto.ReservaCriterialDto;
import com.imagine.gestionsoft.core.dto.ReservaDto;
import com.imagine.gestionsoft.core.service.IReservaService;

@RequestMapping("v1/reserva")
@RestController
public class ReservaController {

	public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

	@Autowired
	private IReservaService iReservaService;

	@GetMapping(path = "{negocio}")
	public ResponseEntity<List<ReservaDto>> consultar(@PathVariable Integer negocio,
			@RequestParam(required = false) Integer cliente, @RequestParam(required = false) Integer colaborador,
			@RequestParam(required = false) Boolean efectiva, @RequestParam(required = false) Boolean estado,
			@RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date fechaCreaFin,
			@RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date fechaCreaIni,
			@RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date fechaFin,
			@RequestParam(required = false) @DateTimeFormat(pattern = DATE_PATTERN) Date fechaIni,
			@RequestParam(required = false) Integer item, @RequestParam(required = false) String observacion) {

		ReservaCriterialDto criterialDto = new ReservaCriterialDto();
		criterialDto.setClienteId(cliente);
		criterialDto.setColaboradorId(colaborador);
		criterialDto.setEfectiva(efectiva);
		criterialDto.setEstado(efectiva);
		criterialDto.setFechaCreacionFin(fechaCreaFin);
		criterialDto.setFechaCreacionFin(fechaCreaIni);
		criterialDto.setFechaFin(fechaFin);
		criterialDto.setFechaIni(fechaIni);
		criterialDto.setItemId(item);
		criterialDto.setObservacion(observacion);

		List<ReservaDto> dtos = iReservaService.obtenerReservasCriterios(criterialDto);

		return new ResponseEntity<List<ReservaDto>>(dtos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ReservaDto> crearReserva(@RequestBody ReservaDto reservaDto) {
		ReservaDto dto = iReservaService.crearReserva(reservaDto);
		return new ResponseEntity<ReservaDto>(dto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ReservaDto> editarReserva(@RequestBody ReservaDto reservaDto) {
		ReservaDto dto = iReservaService.actualizarReserva(reservaDto);
		return new ResponseEntity<ReservaDto>(dto, HttpStatus.OK);
	}

}
