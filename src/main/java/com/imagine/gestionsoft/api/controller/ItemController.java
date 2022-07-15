package com.imagine.gestionsoft.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.imagine.gestionsoft.core.dto.ItemDto;
import com.imagine.gestionsoft.core.service.IItemService;

@RequestMapping("v1/item")
@RestController
public class ItemController {

	@Autowired
	private IItemService itemService;

	@GetMapping(path = "{id}")
	public ResponseEntity<ItemDto> consultarItem(@PathVariable Integer id) {
		ItemDto resp = itemService.obtenerItem(id);
		return ResponseEntity.ok(resp);
	}

	@GetMapping(path = "/negocio/{id}")
	public ResponseEntity<List<ItemDto>> consultarItemsNegocio(@PathVariable Integer id) {
		List<ItemDto> listResp = itemService.obtenerItemsNegocio(id);
		return new ResponseEntity<List<ItemDto>>(listResp, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ItemDto>> consultarItemsNegocioCodigo(@RequestParam Integer negocio,
			@RequestParam String codigo) {

		List<ItemDto> listResp = itemService.obtenerItemsNegocioCodigo(negocio, codigo);
		return ResponseEntity.ok(listResp);
	}

	@PostMapping
	public ResponseEntity<ItemDto> crearItem(@RequestBody ItemDto itemDto) {
		ItemDto resp = itemService.crearItem(itemDto);
		return new ResponseEntity<ItemDto>(resp, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ItemDto> editarItem(@RequestBody ItemDto itemDto) {
		ItemDto resp = itemService.editarItem(itemDto);
		return new ResponseEntity<ItemDto>(resp, HttpStatus.CREATED);
	}

}
