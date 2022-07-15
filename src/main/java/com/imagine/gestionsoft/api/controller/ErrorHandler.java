package com.imagine.gestionsoft.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.imagine.gestionsoft.core.exception.GestionCampoException;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> methodArgumentNotValidEx(MethodArgumentNotValidException ex) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldsEr = result.getFieldErrors();
		Map<String, Object> err = new HashMap<String, Object>();

		StringBuilder msg = new StringBuilder();
		fieldsEr.forEach(x -> msg.append(x.getField() + " " + x.getDefaultMessage() + ", "));

		err.put("codigo", "10");
		err.put("mensaje", msg);

		return new ResponseEntity<Map<String, Object>>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GestionCampoException.class)
	public ResponseEntity<Map<String, Object>> gestionCampoExceptionHandler(GestionCampoException ex) {
		Map<String, Object> err = new HashMap<String, Object>();

		err.put("codigo", ex.getCodigo());
		err.put("mensaje", ex.getMensaje());

		return new ResponseEntity<Map<String, Object>>(err, HttpStatus.BAD_REQUEST);
	}

}
