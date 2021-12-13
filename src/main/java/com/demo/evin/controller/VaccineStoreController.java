package com.demo.evin.controller;

import javax.validation.constraints.PaylaodValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.evin.model.VaccineStorePayload;
import com.demo.evin.service.VaccineStoreService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/vaccine-store")
@Slf4j
public class VaccineStoreController {
	@Autowired
	private VaccineStoreService vaccineStoreService;
	
	@PostMapping(value = "/saveorupdate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<?> saveOrUpdateCustomer(@RequestBody VaccineStorePayload vaccineStorePayload, BindingResult result) {
		if (result.hasFieldErrors()) {
			log.error("Error while parsing Customer Request Json {}", result.getFieldError());
			return new ResponseEntity<>(PaylaodValidator.setErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
	log.debug("save or update customer : {}", vaccineStorePayload);
	return vaccineStoreService.saveVaccineStore(vaccineStorePayload);
	}
	

}
