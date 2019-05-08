package com.assignment.matrixprocessorapp.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.matrixprocessorapp.entity.Matrix;
import com.assignment.matrixprocessorapp.exceptions.BadRequestApiException;
import com.assignment.matrixprocessorapp.services.MatrixService;

/**
 * Rest controller responsible for mapping Api request related Matrix resource
 * 
 * @author gauravs
 *
 */
@RestController
@RequestMapping("/api/v1/")
public class MatrixRestController {

	private final Logger log = LoggerFactory.getLogger(MatrixRestController.class);
	private static final String lONGEST_SUBMATRIX_URL = "/longestSubMatrixes";
	@Autowired
	private MatrixService matService;

	@PostMapping(value = lONGEST_SUBMATRIX_URL, consumes = "Application/json", produces = "Application/json")
	public ResponseEntity<Matrix> getLongestSubMatrix(@RequestBody @Valid Matrix inputMatrix, Errors error)
			throws BadRequestApiException {
		if (error.hasErrors()) {
			log.error("Request is not valid hence returning Http bad request error code");
			throw new BadRequestApiException(HttpStatus.BAD_REQUEST, error.getAllErrors().get(0).getDefaultMessage(),
					error.getAllErrors().get(0).getDefaultMessage());

		}
		Matrix resultMatrix = matService.getLongestSubMatrix(inputMatrix.getElementSet());
		return new ResponseEntity<Matrix>(resultMatrix, HttpStatus.CREATED);
	}

	@ExceptionHandler({ BadRequestApiException.class })
	public ResponseEntity<String> handleMethodArgumentNotValid(BadRequestApiException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}