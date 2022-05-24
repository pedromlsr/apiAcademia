package com.residencia.academia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.residencia.academia.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
		return ResponseEntity.notFound().header("x-error-msg", exception.getMessage()).build();
	}
	
}
